# 44. Wildcard Matching

## Description
- [Link](https://leetcode.com/problems/wildcard-matching/)
- Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
  - '?' Matches any single character. 
  - '*' Matches any sequence of characters (including the empty sequence).

## Solution
### Key Idea
- How many characters can '*' represent? -> Brute force
- Given `s = aaaaa` and `p = **a`, if the two `*`s match `aaa`, there are multiple ways to split it: `"a" + "aa"` or `"aa" + "a"`
  - This results in duplicate computations -> Dynamic programming

### Pseudo Code
```
p = removeConsecutiveStars(p) # if p is "a***b", then removeConsecutiveStars returns "a*b"
if isImpossible(s, p): # if p contains a character whose frequency exceeds that in s, it's impossible to match
    return false
return isMatch(dp, s, 0, p, 0)

boolean isMatch(int[][] dp, String s, int sI, String p, int pI):
    if sI == s.length():
        if pI == p.length() or (pI == p.length() - 1 and p.charAt(pI) == '*'):
            return true
        return false

    if pI == p.length():
        return false

    if dp[sI][pI] != 0:
        return dp[sI][pI] == 1

    boolean ret = false
    if p.charAt(pI) == '*':
        ret = isMatch(dp, s, sI, p, pI + 1) or isMatch(dp, s, sI + 1, p, pI)
    else if p.charAt(pI) == '?' or p.charAt(pI) == s.charAt(sI):
        ret = isMatch(dp, s, sI + 1, p, pI + 1)

    dp[sI][pI] = 1 if ret else -1
    return ret
```

### Time Complexity
- O(N × M) where N is the length of input string s, M is the length of pattern p

