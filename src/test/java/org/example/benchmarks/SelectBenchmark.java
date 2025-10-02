package org.example.benchmarks;

import org.example.algorithms.DeterministicSelect;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SelectBenchmark {

    @Param({"1000", "10000", "100000"})
    private int n;

    private int[] data;
    private int k;

    @Setup(Level.Invocation)
    public void setup() {
        Random random = new Random();
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt();
        }
        k = random.nextInt(n);
    }

    @Benchmark
    public int selectWithSort() {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        return copy[k];
    }

    @Benchmark
    public int selectWithDeterministicSelect() {
        return DeterministicSelect.select(data, k);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SelectBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}