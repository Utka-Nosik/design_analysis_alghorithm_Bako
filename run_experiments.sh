#!/bin/bash

# Очищаем предыдущие результаты
> results.csv

# Размеры входных данных для сортировок
SIZES_SORT=(1000 5000 10000 20000 50000 100000 200000)

# Размеры входных данных для Closest Pair (он медленнее)
SIZES_CLOSEST=(1000 2000 4000 8000 16000)

echo "--- Running Sorting Algorithms ---"
for algo in mergesort quicksort; do
    for size in "${SIZES_SORT[@]}"; do
        echo "Running $algo with size $size..."
        mvn -q exec:java -Dexec.args="$algo $size"
    done
done

echo "--- Running Closest Pair Algorithm ---"
for size in "${SIZES_CLOSEST[@]}"; do
    echo "Running closest with size $size..."
    mvn -q exec:java -Dexec.args="closest $size"
done

echo "--- Experiments finished. Data is in results.csv ---"