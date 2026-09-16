import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

interface Function {
    double evaluate(double x);
}

final class PolynomialFunction implements Function {
    @Override
    public double evaluate(double x) {
        return 2.0 * x * x + 3.0 * x + 0.5;
    }
}

final class TrapezoidalIntegrator {
    private final Function function;
    private final int threadCount;

    TrapezoidalIntegrator(Function function, int threadCount) {
        if (threadCount < 1) {
            throw new IllegalArgumentException("La cantidad de hilos debe ser positiva.");
        }
        this.function = function;
        this.threadCount = threadCount;
    }

    double integrate(double a, double b, long intervals) {
        if (b <= a || intervals < threadCount) {
            throw new IllegalArgumentException("El intervalo o la cantidad de subintervalos no es valida.");
        }

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        List<Future<Double>> partialResults = new ArrayList<>();
        long intervalsPerThread = intervals / threadCount;
        long remainder = intervals % threadCount;
        long intervalStart = 0;
        double step = (b - a) / intervals;

        try {
            for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
                long localIntervals = intervalsPerThread + (threadIndex < remainder ? 1 : 0);
                long localStart = intervalStart;
                long localEnd = localStart + localIntervals;
                partialResults.add(pool.submit(new TrapezoidTask(a, step, localStart, localEnd)));
                intervalStart = localEnd;
            }

            double total = 0.0;
            for (Future<Double> result : partialResults) {
                total += result.get();
            }
            return total;
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("La integracion fue interrumpida.", exception);
        } catch (ExecutionException exception) {
            throw new IllegalStateException("Fallo un hilo de integracion.", exception.getCause());
        } finally {
            pool.shutdown();
        }
    }

    private final class TrapezoidTask implements Callable<Double> {
        private final double start;
        private final double step;
        private final long firstInterval;
        private final long lastInterval;

        TrapezoidTask(double a, double step, long firstInterval, long lastInterval) {
            this.start = a;
            this.step = step;
            this.firstInterval = firstInterval;
            this.lastInterval = lastInterval;
        }

        @Override
        public Double call() {
            double area = 0.0;
            for (long interval = firstInterval; interval < lastInterval; interval++) {
                double x1 = start + interval * step;
                double x2 = x1 + step;
                area += (function.evaluate(x1) + function.evaluate(x2)) * step / 2.0;
            }
            return area;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        double a = 2.0;
        double b = 20.0;
        long intervals = 1_000_000;
        int threads = Math.max(1, Runtime.getRuntime().availableProcessors());

        TrapezoidalIntegrator integrator = new TrapezoidalIntegrator(
                new PolynomialFunction(), threads);
        double area = integrator.integrate(a, b, intervals);
        double exactArea = 5931.0;

        System.out.printf("Java - Metodo del trapecio%n");
        System.out.printf("Intervalo: [%.1f, %.1f]%n", a, b);
        System.out.printf("Subintervalos: %d%n", intervals);
        System.out.printf("Hilos del pool: %d%n", threads);
        System.out.printf("Area aproximada: %.10f%n", area);
        System.out.printf("Area exacta: %.10f%n", exactArea);
        System.out.printf("Error absoluto: %.10f%n", Math.abs(exactArea - area));
    }
}
