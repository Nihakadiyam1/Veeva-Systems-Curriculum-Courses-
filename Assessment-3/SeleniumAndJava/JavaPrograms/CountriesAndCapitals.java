import java.util.*;

public class CountriesAndCapitals {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Country mapping
        HashMap<String, HashMap<String, String>> map = new HashMap<>();

        System.out.print("Enter number of countries: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("\nEnter name of the country: ");
            String country = sc.nextLine();

            System.out.print("Enter number of capitals of the country: ");
            int capCount = sc.nextInt();
            sc.nextLine();

            HashMap<String, String> capitalMap = new HashMap<>();

            for (int j = 0; j < capCount; j++) {
                System.out.print("Enter season : ");
                String season = sc.nextLine();

                System.out.print("Enter name of the capital: ");
                String capital = sc.nextLine();

                capitalMap.put(season, capital);
            }

            map.put(country, capitalMap);
        }

        // 1. Printing all countries
        System.out.println("\n---- All Countries ----");
        for (String country : map.keySet()) {
            System.out.println("Country: " + country);
            System.out.println("Number of Capitals: " + map.get(country).size());

            for (String season : map.get(country).keySet()) {
                System.out.println(season + " -> " + map.get(country).get(season));
            }
            System.out.println();
        }

        // 2. Giving counrty name to search
        System.out.print("Enter country name to search: ");
        String searchCountry = sc.nextLine();

        if (map.containsKey(searchCountry)) {
            System.out.println("Number of Capitals: " + map.get(searchCountry).size());
            for (String season : map.get(searchCountry).keySet()) {
                System.out.println(season + " -> " + map.get(searchCountry).get(season));
            }
        } else {
            System.out.println("Country not found");
        }

        // 3. Countries with 2 capitals
        System.out.println("\nCountries with 2 Capitals:");
        for (String country : map.keySet()) {
            if (map.get(country).size() == 2) {
                System.out.println(country);
            }
        }

        // 4. Countries with 3 capitals
        System.out.println("\nCountries with 3 Capitals:");
        for (String country : map.keySet()) {
            if (map.get(country).size() == 3) {
                System.out.println(country);
            }
        }

        // 5. Countries without Summer capital
        System.out.println("\nCountries without Summer as a Capital:");
        for (String country : map.keySet()) {
            if (!map.get(country).containsKey("Summer")) {
                System.out.println(country);
            }
        }

        // 6. Capitals of countries starting with vowel
        System.out.println("\nCapitals of Countries Starting with Vowel:");
        for (String country : map.keySet()) {
            char ch = Character.toLowerCase(country.charAt(0));
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                for (String season : map.get(country).keySet()) {
                    System.out.println(country + " -> " + map.get(country).get(season));
                }
            }
        }

        // 7. Summer capitals of vowel countries
        System.out.println("\nSummer Capitals of Vowel Countries:");
        for (String country : map.keySet()) {
            char ch = Character.toLowerCase(country.charAt(0));
            if ((ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                    && map.get(country).containsKey("Summer")) {
                System.out.println(country + " -> " + map.get(country).get("Summer"));
            }
        }

        sc.close();
    }
}