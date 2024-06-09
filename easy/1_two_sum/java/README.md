# Two Sum Solution

[Link to the problem](https://leetcode.com/problems/two-sum/description/)

## Solution

The provided solution uses a single-pass hash map to achieve an O(n) time complexity.

### Approach:

1. Create a hash map to store the value and its corresponding index from the `nums` array.
2. Iterate through the `nums` array.
3. For each element in the array, calculate its complement with respect to the `target` (i.e., `complement = target - nums[i]`).
4. Check if the complement exists in the hash map.
5. If it exists, return the indices of the current element and the complement.
6. If it does not exist, add the current element and its index to the hash map.
7. If no solution is found, return null (though the problem guarantees exactly one solution).

### Java Code:

```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}
```
# Explanation:
Hash Map Creation: A hash map (map) is created to store each element's value and its index.
- Iteration: We iterate through the nums array using a for loop.
- Complement Calculation: For each element, we calculate the complement which, when added to the current element, equals the target.
- Complement Check: We check if the complement is already in the hash map.
- Return Indices: If the complement exists, we return the indices of the complement and the current element.
- Add to Hash Map: If the complement does not exist, we add the current element and its index to the hash map.
- No Solution: If no solution is found after the loop, we return null (this line should not be reached as per problem constraints).

This approach ensures that we only traverse the array once, leading to an efficient O(n) time complexity.
