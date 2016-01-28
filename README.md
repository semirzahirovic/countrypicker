AndroidCountryPicker
====================

## Features
CountryPicker is a simple fragment that can be embedded or shown as dialog. See the example to see more detail.


<img src="https://raw.github.com/roomorama/AndroidCountryPicker/master/screenshot/1.png" width="250">
<img src="https://raw.github.com/roomorama/AndroidCountryPicker/master/screenshot/2.png" width="250">


The functions are simple:
 
1) Allow user to search the country

2) Inform client which country user has selected

3) Convenient function to get currency code of the selected country

## How to embed

### Using gradle

Define jitpack.io as a repository
```gradle
repositories {
	// ...
    maven { url 'https://jitpack.io' }
	// ...
}
```

Include country picker
```gradle
dependencies {
	// ...
    compile 'com.github.SocialbitGmbH:AndroidCountryPicker:1.0.3@aar'
	// ...
}
```

Note the library requires 'com.android.support:support-v4'


## How to use

To use CountryPicker in your code as a fragment:

```java
FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//The second parameter determines if the country must be auto-selected from the user current location.
CountryPicker picker = CountryPicker.newInstance(null, true);
transaction.replace(R.id.home, picker);
transaction.commit();
```

To show CountryPicker as a dialog:

```java
//The second parameter determines if the country must be auto-selected from the user current location.
CountryPicker picker = CountryPicker.newInstance("Select Country", true);
picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
```

When user selects a country, client can listen to that event:

```java
picker.setListener(new CountryPickerListener() {

	@Override
	public void onSelectCountry(String name, String code) {
		// Invoke your function here
	}
});
```

To receive the data that the picker uses:

```java
CountryProvider countryProvider = new CountryProvider(context);

Country de = countryProvider.getCountryByCode("ES"); // ignores case
Log.d(de.getName()) // Spain

List<Country> countries = countryProvider.getCountries();
```

## About
The data is from CountryPicker by nicklockwood (https://github.com/nicklockwood/CountryPicker)

I converted his data in "Countries.plist" to json format to avoid extra dependency.

Thanks Nick for his awesome library!

Thanks to Javi for the Spanish translation (https://github.com/Mun0n/AndroidCountryPicker)

## License
See LICENSE.md
