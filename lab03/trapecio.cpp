#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <stdexcept>
#include <thread>
#include <vector>

class Function {
public:
    virtual ~Function() = default;
    virtual double evaluate(double x) const = 0;
};

class PolynomialFunction final : public Function {
public:
    double evaluate(double x) const override {
        return 2.0 * x * x + 3.0 * x + 0.5;
    }
};

class TrapezoidalIntegrator {
private:
    const Function& function;
    unsigned int threadCount;

public:
    TrapezoidalIntegrator(const Function& function, unsigned int threadCount)
        : function(function), threadCount(threadCount) {
        if (threadCount == 0) {
            throw std::invalid_argument("La cantidad de hilos debe ser positiva.");
        }
    }

    double integrate(double a, double b, unsigned long long intervals) const {
        if (b <= a || intervals < threadCount) {
            throw std::invalid_argument("El intervalo o la cantidad de subintervalos no es valida.");
        }

        // Memoria dinamica para conservar el resultado de cada hilo.
        double* partialAreas = new double[threadCount]{};
        std::vector<std::thread> workers;
        workers.reserve(threadCount);

        const unsigned long long intervalsPerThread = intervals / threadCount;
        const unsigned long long remainder = intervals % threadCount;
        const double step = (b - a) / static_cast<double>(intervals);
        unsigned long long intervalStart = 0;

        for (unsigned int threadIndex = 0; threadIndex < threadCount; ++threadIndex) {
            const unsigned long long localIntervals = intervalsPerThread +
                (threadIndex < remainder ? 1 : 0);
            const unsigned long long localStart = intervalStart;
            const unsigned long long localEnd = localStart + localIntervals;

            workers.emplace_back([this, partialAreas, threadIndex, a, step,
                                  localStart, localEnd]() {
                double area = 0.0;
                for (unsigned long long interval = localStart; interval < localEnd; ++interval) {
                    const double x1 = a + interval * step;
                    const double x2 = x1 + step;
                    area += (function.evaluate(x1) + function.evaluate(x2)) * step / 2.0;
                }
                partialAreas[threadIndex] = area;
            });
            intervalStart = localEnd;
        }

        for (std::thread& worker : workers) {
            worker.join();
        }

        double total = 0.0;
        for (unsigned int threadIndex = 0; threadIndex < threadCount; ++threadIndex) {
            total += partialAreas[threadIndex];
        }
        delete[] partialAreas;
        return total;
    }
};

int main() {
    try {
        const double a = 2.0;
        const double b = 20.0;
        const unsigned long long intervals = 1'000'000;
        const unsigned int threads = std::max(1u, std::thread::hardware_concurrency());
        const double exactArea = 5931.0;

        PolynomialFunction function;
        TrapezoidalIntegrator integrator(function, threads);
        const double area = integrator.integrate(a, b, intervals);

        std::cout << std::fixed << std::setprecision(10);
        std::cout << "C++ - Metodo del trapecio\n";
        std::cout << "Intervalo: [" << a << ", " << b << "]\n";
        std::cout << "Subintervalos: " << intervals << "\n";
        std::cout << "Hilos: " << threads << "\n";
        std::cout << "Area aproximada: " << area << "\n";
        std::cout << "Area exacta: " << exactArea << "\n";
        std::cout << "Error absoluto: " << std::abs(exactArea - area) << "\n";
    } catch (const std::exception& exception) {
        std::cerr << "Error: " << exception.what() << '\n';
        return 1;
    }
    return 0;
}
