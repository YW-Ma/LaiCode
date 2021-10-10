#include<iostream>
#include<vector>
using namespace std;
int missing(vector<int> &nums, int idx);
int missingElement(vector<int> &nums, int k);
int main() {
    int ns[4] = {4,7,9,10};
    vector<int> nums;
    for (int i = 0; i < 4; i++) {
        nums.push_back(ns[i]);
    }
    cout << missingElement(nums, 3) << endl;
    return 0;
}

int missing(vector<int> &nums, int idx) {
    return nums.at(idx) - nums.at(0) - idx;
}

int missingElement(vector<int> &nums, int k) {
    // corner case:
    if (missing(nums, nums.size() - 1) < k) {
        return nums.back() + (k - missing(nums, nums.size() - 1));
    }

    // binary search
    int left = 0;
    int right = nums.size() - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (missing(nums, mid) < k) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return nums.at(left - 1) + (k - missing(nums, left - 1));
}