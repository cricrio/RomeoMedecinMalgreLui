package background;
public class RomanUtils {
	private final static String[] acceptedchar = { "I", "V", "X", "L", "C", "D", "M" };

	private final static String[] _RRUTR = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
	private final static String[] _RRDTR = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
	private final static String[] _RRCTR = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
	private final static String[] _RRMTR = { "", "M", "MM", "MMM", "", "", "", "", "", "" };

	 int RomanCharToValue(char vv) {
		switch (vv) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		}
		return 0;
	}

	public String RomanToStringValueInt(String RN) {
		int oc, NewRV, OldRV = 0, Result = 0;

		if (IsRomanNumber(RN)) {
			for (oc = 0; oc < RN.length(); oc++) {
				NewRV = RomanCharToValue(RN.toCharArray()[oc]);
				if (NewRV > OldRV) {
					Result += NewRV - (OldRV << 1);
				} else {
					Result += NewRV;
				} // endif
				OldRV = NewRV;
			} // endfor

		} else
			return null;// endif

		return Result+"";
	}

	 boolean IsRomanNumber(String val) {
		boolean actuval;

		for (int i = 0; i < val.length(); i++) {
			actuval = false;
			for (int j = 0; j < acceptedchar.length; j++)
				if (val.substring(i, i + 1).equals(acceptedchar[j]))
					actuval = true;
			if (actuval == false)
				return false;
		}
		return true;
	}

	static boolean IsRomanNumber(int val) {
		if ((val > 0) && (val <= 39999))
			return true;
		return false;
	}

	public  String IntToRoman(int i) {
		if (IsRomanNumber(i)) {

			if (i >= 0 && i <= 9)
				return _RRUTR[i];
			if (i >= 10 && i <= 99)
				return _RRDTR[i / 10] + _RRUTR[i % 10];
			if (i >= 100 && i <= 999)
				return _RRCTR[i / 100] + _RRDTR[(i / 10) % 10] + _RRUTR[i % 10];
			if (i >= 1000 && i <= 3999)
				return _RRMTR[i / 1000] + _RRCTR[(i / 100) % 10] + _RRDTR[(i / 10) % 10] + _RRUTR[i % 10];

		} // endif

		return "";
	}

}