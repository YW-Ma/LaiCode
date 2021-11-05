package com.interview.tiktok;

public class CountAnalogousArray_SecretArray {
    // Definition of SecretArray:
    // 1. the length of array  ==  secret array
    // 2. each integer in the array within [lowerBound, upperBound]
    // 3. each pair of consecutive integers of array --> has a diff equal to the secret array

    // given an array, lowerBound, upperBound
    // return how many possible analogous arrays can be generated.

    // For example:
    //  consecutiveDifference = [-2, -1, -2, 5]
    //  lowerBound = 3
    //  upperBound = 10
    //
    // Note that none of the values is out of the bound. All possible analogous arrays are:
    //  [3, 5, 6, 8, 3]
    //  [4, 6, 7, 9, 4]
    //  [5, 7, 8, 10, 5]
    //
    //  Tha answer is 3.
}
