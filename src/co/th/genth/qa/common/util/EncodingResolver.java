package co.th.genth.qa.common.util;

import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Thanopong.W
 * 
 */
@Service("encodingService")
public class EncodingResolver {

	private Logger logger = Logger.getLogger(EncodingResolver.class);

	public String UnicodeToASCII(String unicode) {
		// initial temporary space of ascii.
		StringBuffer ascii = new StringBuffer(unicode);
		int code;

		// continue loop based on number of character.
		for (int i = 0; i < unicode.length(); i++) {
			// reading a value of each character in the unicode (as String).
			code = (int) unicode.charAt(i);

			// check the value is Thai language in Unicode scope or not.
			if ((0xE01 <= code) && (code <= 0xE5B)) {
				// if yes, it will be converted to Thai language in ASCII scope.
				ascii.setCharAt(i, (char) (code - 0xD60));
			}
		}

		return ascii.toString();
	}

	public String ASCIIToUnicode(String ascii) {
		// initial temporary space of unicode
		StringBuffer unicode = new StringBuffer(ascii);
		int code;

		// continue loop based on number of character.
		for (int i = 0; i < ascii.length(); i++) {
			code = (int) ascii.charAt(i);

			// check the value is Thai language in ASCII scope or not.
			if ((0xA1 <= code) && (code <= 0xFB)) {
				// if yes, it will be converted to Thai language in Unicode
				// scope.
				unicode.setCharAt(i, (char) (code + 0xD60));
			}
		}

		// convert unicode to be as String type to use continue.
		return unicode.toString();
	}

	public String convertToThai(String stringContext) {
		// initial temporary attribute as String type
		String stringTH = "";

		try {
			// convert a string by encoding to 'TIS620' Thai language code based
			// on 'ISO8859_1' in java standard.
			if (stringContext != null && stringContext.length() > 0) {
				stringTH = new String(stringContext.trim().getBytes("ISO-8859-1"), "TIS-620");
			}
		} catch (Exception ex) {
			logger.error("Convert To Thai is an error :", ex);
		}

		return stringTH;
	}

	public String convertToThai(InputStreamReader in, String text) {
		// initial temporary attribute as String type
		String stringTH = "";

		try {
			// convert a string by encoding to 'TIS620' Thai language code based
			// on 'ISO8859_1' in java standard.
			if (text != null && text.length() > 0) {
				stringTH = new String(text.trim().getBytes(in.getEncoding()), "TIS-620");
			}
		} catch (Exception ex) {
			logger.error("Convert To Thai is an error :", ex);
		}

		return stringTH;
	}
}
