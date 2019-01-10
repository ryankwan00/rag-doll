import java.io.*;
import java.security.InvalidParameterException;

public class IO
{
	private static BufferedReader kb =
		new BufferedReader(new InputStreamReader(System.in));

	public static String readString(String p){
		System.out.print(p);
		return readString();
	}

	public static String readString()
	{
		while (true) {
			try {
				return kb.readLine();
			} catch (IOException e) {
				// should never happen
			}
		}
	}


	public static int readInt(String p){
		return readInt(p,Integer.MAX_VALUE);
	}

	public static int readInt(String p,int max){
		System.out.print(p);
		return readInt(1,max);
	}

	public static int readInt(int min,int max)
	{
		while (true) {
			try {
				String s = kb.readLine();
				int value=Integer.parseInt(s);
				if(value>max||value<min){
					throw new InvalidParameterException();
				}
				return value;
			}
			catch (NumberFormatException e) {
				System.out.print("That is not an integer.  Enter again: ");
			}catch (InvalidParameterException e){
				System.out.print("That is an invalid integer.  Enter again: ");
			}
			catch (IOException e) {
				// should never happen
			}
		}
	}

	public static int readInt()
	{
		return readInt(Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	public static double readDouble()
	{
		while (true) {
			try {
				String s = kb.readLine();
				return Double.parseDouble(s);
			} catch (NumberFormatException e) {
				System.out.print("That is not a number.  Enter again: ");
			} catch (IOException e) {
				// should never happen
			}
		}
	}

	public static char readChar()
	{
		String s = null;

		try {
			s = kb.readLine();
		} catch (IOException e) {
			// should never happen
		}

		while (s.length() != 1) {
			System.out.print("That is not a single character.  Enter again: ");
			try {
				s = kb.readLine();
			} catch (IOException e) {
				// should never happen
			}
		}

		return s.charAt(0);
	}

        public static boolean readBoolean()
        {
                String s = null;
 
                while (true) {
                        try {
                                s = kb.readLine();
                        } catch (IOException e) {
                                // should never happen
                        }
 
                        if (s.equalsIgnoreCase("yes") ||
			    s.equalsIgnoreCase("y") ||
			    s.equalsIgnoreCase("true") ||
			    s.equalsIgnoreCase("t")) {
                                return true;
                        } else if (s.equalsIgnoreCase("no") ||
			           s.equalsIgnoreCase("n") ||
			           s.equalsIgnoreCase("false") ||
			           s.equalsIgnoreCase("f")) {
                                return false;
                        } else {
                                System.out.print("Enter \"yes\" or \"no\": ");
                        }
                }
        }
}
