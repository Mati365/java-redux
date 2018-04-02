/* file name  : src/main/java/com/mati365/calc/utils/Matrix.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 19:41:45 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.Arrays;

import java.awt.Point;
import java.awt.Dimension;

import java.lang.reflect.Array;
import java.lang.IllegalStateException;

import org.apache.commons.lang3.ArrayUtils;
import javax.validation.constraints.NotNull;

public class Matrix<T extends Number> implements Cloneable {
    private T[][] array = null;
    private Dimension dimensions = null;
    
    /**
     * Default constructor used during deserlaize 
     */
    public Matrix() {}

    /** 
     * Copy constructor of matrix class 
     * 
     * @param matrix 
     */
    @SuppressWarnings("unchecked")
    public Matrix(Class<? extends T> cellType, @NotNull Matrix<T> matrix) {
        dimensions = (Dimension) matrix.dimensions.clone();
        array = (T[][]) Array.newInstance(cellType, dimensions.width, dimensions.height);  
        
        for (int i = 0; i < dimensions.height; ++i) {
            System.arraycopy(
                matrix.array[i],
                0,
                array[i],
                0,
                dimensions.width
            ); 
        }
    }

    /**
     * Creates matrix from array, dimensions is inherited
     *
     * @param array     Matrix value array
     * @throws IllegalStateException 
     */
    public Matrix(@NotNull T[][] array) throws IllegalStateException { 
        if (ArrayUtils.isNotEmpty(array))
            throw new IllegalStateException("Matrix array cannot be empty!");

        array = array;
        dimensions = new Dimension(array[0].length, array.length);
    }

    /**
     * Due to java generics array problems force cast Array.newInstance to generics type
     *
     * @param dimensions        Dimensions of matrix
     * @param cellType          Cell class type         
     */
    @SuppressWarnings("unchecked")
    public Matrix(Dimension dimensions, Class<? extends T> cellType) {
        this.dimensions = dimensions;
        this.array = (T[][]) Array.newInstance(cellType, dimensions.height, dimensions.width);  
    }

    public void setArray(T[][] array) { this.array = array; }
    public void setDimensions(@NotNull Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public T[][] getArray()             { return array; }
    public Dimension getDimensions()    { return dimensions; }

    @JsonIgnore public int getWidth()       { return dimensions.width; }
    @JsonIgnore public int getHeight()      { return dimensions.height; }    
    @JsonIgnore public int getLength()      {
        return dimensions.width * dimensions.height;
    }

    public T getCellAt(@NotNull Point point) {
        return array[point.y][point.x];
    }

    /** 
     * @param 
     * @throws IndexOutOfBoundsException 
     */
    public Matrix<T> cellOperation(
            @NotNull Point cell,
            @NotNull Function<T, T> operator) throws IndexOutOfBoundsException {
        if (cell.x >= dimensions.width 
                || cell.y >= dimensions.height 
                || cell.x < 0 
                || cell.y < 0) {
            throw new IndexOutOfBoundsException("Illegal matrix coordinate!");
        }

        this.array[cell.y][cell.x] = operator.apply(this.array[cell.y][cell.x]);
        return this;
    }
    
    /** 
     * Loads value into cell  
     * 
     * @param cell 
     * @param value 
     * @return 
     * @throws IndexOutOfBoundsException 
     */
    public Matrix<T> load(@NotNull Point cell, T value) throws IndexOutOfBoundsException {
        return cellOperation(cell, (prev) -> value);
    }
        
    /** 
     * Fills whole matrix with whole value
     * 
     * @param value 
     * @return 
     */
    public Matrix<T> fill(T value) {
        for (T[] row : this.array)
            Arrays.fill(row, value);
        return this;
    }
     
    /** 
     * Clear whole array with null 
     * 
     * @return 
     */
    public Matrix<T> clear() { return fill(null); }

    /** 
     * Reduces whole array into single value i
     * by invoking reducer on each child 
     * 
     * @param acc 
     * @param reducer 
     * @param reducer 
     * @return 
     */
    public T reduce(T acc, @NotNull BiFunction<T, T, T> reducer) {
        T buffer = acc;
        for (T[] row : this.array) {
            for (T cell : row)
                buffer = reducer.apply(buffer, cell);
        }
        return buffer;
    }
}


