package org.thomas.jit;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.All)
@State(Scope.Thread)
public class JMHBnhSqrt {
    private int cnt;
    private double MM;

    private int NN;
   
    private int jj;
    private int ii;
   
    private double kk;

    private double sumii;
    private double sumjj;
    private double sumkk;

    @Setup
    public void init() {
        MM = 2855.0;
        NN = 1_0000_0000;
       
        cnt = 0;
       
        ii=0;
        sumii=0;
       
        jj = 0;
        sumjj = 0;
       
        kk=0;
        sumkk=0;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void sqrt() {
        Math.sqrt(MM + cnt++);
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchLoopDD() {
        kk=0;
        for (int i = 0; i < NN; i++) {
            sumkk += Math.sqrt(MM + kk++);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchLoopDI() {
        for (int i = 0; i < NN; i++) {
            sumkk += Math.sqrt(MM + i);
        }
    }
   
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchLoopDIJ() {
        jj=0;
        for (int i = 0; i < NN; i++) {
            sumjj += Math.sqrt(MM + jj++);
        }
    }
   
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchLoopII() {
        ii=0;
        for (int i = 0; i < NN; i++) {
            sumii += Math.sqrt(285 + ii++);
        }
    }
   
    @TearDown
    public void end() {
        System.out.println(String.format("%s,%s,%s", sumii,sumjj,sumkk));
    }

    public static void main(String[] args) throws Exception {
        int thread_c = 1;
        int forks = 1;
        if (null != args && args.length > 0) {
            thread_c = Integer.valueOf(args[0]);
        }
        if (null != args && args.length > 1) {
            forks = Integer.valueOf(args[1]);
        }
        // JMHTest.class.getName()
        Options options = new OptionsBuilder().include("JMHBn*").threads(thread_c).forks(forks).warmupIterations(2)
                .measurementIterations(3).build();
        new Runner(options).run();
    }

}

