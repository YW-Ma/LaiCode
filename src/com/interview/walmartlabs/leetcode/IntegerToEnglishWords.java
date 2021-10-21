package com.interview.walmartlabs.leetcode;

public class IntegerToEnglishWords {
    String[] singles = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    StringBuilder sb;

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        sb = new StringBuilder();
        helper(num);
        sb.deleteCharAt(sb.length() - 1); // 把最后一个空格删掉
        return sb.toString();
    }

    public void helper(int n) {
        int billion = n / 1000000000;
        n %= 1000000000;
        int million = n / 1000000;
        n %= 1000000;
        int thousand = n / 1000;
        n %= 1000;

        if (billion != 0) {
            sb.append(comma(billion)).append("Billion ");
        }
        if (million != 0) {
            sb.append(comma(million)).append("Million ");
        }
        if (thousand != 0) {
            sb.append(comma(thousand)).append("Thousand ");
        }
        sb.append(comma(n));
        return;
    }

    public String comma(int n) {
        StringBuilder ans = new StringBuilder();
        int hundred = n / 100;
        if (hundred != 0) {
            ans.append(singles[hundred]).append(" Hundred ");
        }

        n = n % 100;
        if (n < 20 && n >= 10) {  // 对于teen，直接包括了十位和个位
            ans.append(teens[n%10] + " ");
            return ans.toString();
        }

        if (n >= 20) { // 对于其他的，要把十位和个位拆开来
            ans.append(tens[n/10] + " ");
        }
        n = n % 10;
        if (n != 0) {
            ans.append(singles[n%10] + " ");
        }
        return ans.toString();
    }
}