package com.cs333.stringsearch;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@OperationsPerInvocation(1)
@Measurement(iterations = 5)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class BenchmarkTests {
    @Param({"", "GCAGAGAG", "TEST"})
    private static String PARTIAL_STRING;

    @Param({"", "GCATCGCAGAGAGTATACAGTACGGCAGAGAG", "TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST"})
    private static String FULL_STRING;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkTests.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void measureZhu() {
        new ZhuTakaoka().search(PARTIAL_STRING, FULL_STRING);
    }

    @Benchmark
    public void measureBM() {
        new BoyerMoore().search(PARTIAL_STRING, FULL_STRING);
    }

    @Benchmark
    public void measureKMP() {
        new KnuthMorrisPratt().search(PARTIAL_STRING, FULL_STRING);
    }

    @Benchmark
    public void measureKR() {
        new KarpRapin().search(PARTIAL_STRING, FULL_STRING);
    }
}
