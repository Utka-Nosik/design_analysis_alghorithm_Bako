# design_analysis_alghorithm_Bako
# Assignment 1 Report

## 1. Architecture Notes

*   **Recursion Depth Control**: Recursion depth is tracked using a `ThreadLocal` variable in `DepthTracker`. This ensures thread safety and correct depth measurement even in parallel execution environments. For robust algorithms like QuickSort, recursion is performed on the smaller partition first, while the larger partition is handled iteratively. This technique guarantees a maximum recursion depth of O(log n).
*   **Memory Allocation Control**: Memory allocations are minimized through techniques like using a reusable buffer in MergeSort. Instead of creating a new auxiliary array for each recursive call, a single buffer is created once and passed down through the call stack. For `DeterministicSelect`, allocations are primarily for the array of medians, which is significantly smaller than the input array.

## 2. Recurrence Analysis

### MergeSort
- **Recurrence**: `T(n) = 2T(n/2) + Θ(n)`
- **Analysis**: The algorithm divides the array into two halves (`2T(n/2)`) and performs a linear-time merge operation (`Θ(n)`). According to the Master Theorem (Case 2, since `n^log_b(a) = n^log_2(2) = n^1`), the solution is `Θ(n log n)`.

### QuickSort
- **Recurrence (Worst Case)**: `T(n) = T(n-1) + Θ(n)`
- **Analysis (Worst Case)**: This occurs with a consistently bad pivot (e.g., smallest element), leading to a `Θ(n^2)` runtime. However, using a randomized pivot makes this scenario extremely unlikely.
- **Recurrence (Expected Case)**: `T(n) ≈ 2T(n/2) + Θ(n)`
- **Analysis (Expected Case)**: With a random pivot, the partition is expected to be reasonably balanced. This leads to an average-case runtime of `Θ(n log n)`, similar to MergeSort.

### Deterministic Select (Median-of-Medians)
- **Recurrence**: `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)`
- **Analysis**: The algorithm finds the median of medians of groups of 5 (`T(n/5)`), partitions the array, and then recurses on a partition that is at most `7n/10` in size. The work done at each step is linear (`Θ(n)`). Since `1/5 + 7/10 = 9/10 < 1`, the work done at each level of recursion decreases geometrically. This leads to a total runtime of `Θ(n)`.

### Closest Pair of Points
- **Recurrence**: `T(n) = 2T(n/2) + O(n)`
- **Analysis**: The algorithm divides the set of points into two halves (`2T(n/2)`) and recursively finds the closest pair in each. The "conquer" step involves checking points in a vertical "strip" of width `2δ`. This step can be performed in `O(n)` time (by pre-sorting points by y-coordinate, though our simple version re-sorts, making it `O(n log n)` in the conquer step). Assuming a linear-time strip check, the Master Theorem (Case 2) gives a runtime of `O(n log n)`.

---
