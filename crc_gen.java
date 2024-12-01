import java.io.*;

class CRCGen {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Declare arrays for data, divisor, remainder, and CRC
        int[] data;
        int[] divisor;
        int[] rem;
        int[] crc;
        int dataBits, divisorBits, totalLength;

        // Input the number of data bits
        System.out.println("Enter number of data bits: ");
        dataBits = Integer.parseInt(br.readLine());
        data = new int[dataBits];

        // Input the data bits
        System.out.println("Enter data bits: ");
        for (int i = 0; i < dataBits; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        // Input the number of divisor bits
        System.out.println("Enter number of bits in divisor: ");
        divisorBits = Integer.parseInt(br.readLine());
        divisor = new int[divisorBits];

        // Input the divisor bits
        System.out.println("Enter divisor bits: ");
        for (int i = 0; i < divisorBits; i++) {
            divisor[i] = Integer.parseInt(br.readLine());
        }

        // Print the data and divisor bits
        System.out.print("Data bits are: ");
        for (int i = 0; i < dataBits; i++) {
            System.out.print(data[i]);
        }
        System.out.println();

        System.out.print("Divisor bits are: ");
        for (int i = 0; i < divisorBits; i++) {
            System.out.print(divisor[i]);
        }
        System.out.println();

        // Calculate the total length for CRC (data bits + divisor bits - 1)
        totalLength = dataBits + divisorBits - 1;
        int[] div = new int[totalLength];
        rem = new int[totalLength];
        crc = new int[totalLength];

        // CRC Generation
        for (int i = 0; i < data.length; i++) {
            div[i] = data[i];
        }

        System.out.print("Dividend (after appending 0's) are: ");
        for (int i = 0; i < div.length; i++) {
            System.out.print(div[i]);
        }
        System.out.println();

        // Copy dividend to remainder
        for (int j = 0; j < div.length; j++) {
            rem[j] = div[j];
        }

        // Perform division and store result in rem
        rem = divide(div, divisor, rem);

        // XOR the dividend with remainder to get CRC
        for (int i = 0; i < div.length; i++) {
            crc[i] = (div[i] ^ rem[i]);
        }

        // Print the CRC code
        System.out.println("CRC code: ");
        for (int i = 0; i < crc.length; i++) {
            System.out.print(crc[i]);
        }

        // Error detection
        System.out.println();
        System.out.println("Enter CRC code of " + totalLength + " bits: ");
        for (int i = 0; i < crc.length; i++) {
            crc[i] = Integer.parseInt(br.readLine());
        }

        // Check for errors by dividing the received CRC code
        for (int j = 0; j < crc.length; j++) {
            rem[j] = crc[j];
        }

        rem = divide(crc, divisor, rem);

        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                System.out.println("Error");
                break;
            }
            if (i == rem.length - 1) {
                System.out.println("No Error");
            }
        }

        System.out.println("Thank you.");
    }

    // Division method for CRC calculation
    static int[] divide(int[] div, int[] divisor, int[] rem) {
        int cur = 0;

        while (true) {
            // XOR divisor with remainder
            for (int i = 0; i < divisor.length; i++) {
                rem[cur + i] = (rem[cur + i] ^ divisor[i]);
            }

            // Skip leading zeros in remainder
            while (rem[cur] == 0 && cur != rem.length - 1) {
                cur++;
            }

            // If remainder length is less than divisor length, break the loop
            if ((rem.length - cur) < divisor.length) {
                break;
            }
        }

        return rem;
    }
}