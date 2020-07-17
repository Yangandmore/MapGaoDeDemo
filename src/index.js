import React from 'react';
import {
	View
} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { ThemeProvider } from 'react-native-elements';
import Home from './home';
import Examples from './example';

const Stack = createStackNavigator(); 

const App = () => (
	<ThemeProvider>
		<NavigationContainer>
			<Stack.Navigator>
				<Stack.Screen name='地图功能' component={Home}/>
				{Object.keys(Examples).map(name => (
					<Stack.Screen key={name} name={name} component={Examples[name]} />
				))}
			</Stack.Navigator>
		</NavigationContainer>
  </ThemeProvider>
);

export default App;
