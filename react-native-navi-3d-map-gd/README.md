
# react-native-react-native-map-gd

## Getting started

`$ npm install react-native-react-native-map-gd --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-map-gd`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-react-native-map-gd` and add `RNReactNativeMapGd.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativeMapGd.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeMapGdPackage;` to the imports at the top of the file
  - Add `new RNReactNativeMapGdPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-map-gd'
  	project(':react-native-react-native-map-gd').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-map-gd/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-map-gd')
  	```


## Usage
```javascript
import RNReactNativeMapGd from 'react-native-react-native-map-gd';

// TODO: What to do with the module?
RNReactNativeMapGd;
```
  