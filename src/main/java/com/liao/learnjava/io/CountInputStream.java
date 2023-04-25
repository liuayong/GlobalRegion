package com.liao.learnjava.io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/24
 **/
public class CountInputStream extends FilterInputStream {
    private int count = 0;
    
    public CountInputStream(InputStream in) {
        super(in);
        if (!in.markSupported()) {
            this.in = new BufferedInputStream(in);
        }
    }
    
    @Override
    public synchronized void reset() throws IOException {
        System.out.println("available " + in.available());
        if (!in.markSupported()) {
            in = new BufferedInputStream(in);
        }
        in.reset();
    }
    
    public int getBytesRead() {
        return this.count;
    }
    
    @Override
    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            count++;
        }
        return n;
    }
    
    public int read(@NotNull byte[] b) throws IOException {
        System.err.println("b = " + b + ", length = " + b.length);
        return super.read(b);
    }
    
    @Override
    public int read(@NotNull byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n != -1) {
            count += n;
        }
        return n;
    }
}
