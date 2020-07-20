import React from 'react';
import {
	View,
	PermisstionsAndroid,
	Picker,
	SafeAreaView
} from 'react-native';
import { GDMapView } from 'react-native-navi-3d-map-gd';


export default ({ navigation, route }) => {

	const { params = { mapType: 'normal' } } = route;
	const { mapType } = params;
	
	// const headerRightProps = {
	// 	mode: 'dropdown',
	// 	style: { width: 100 },
	// 	selectedValue: mapType,
	// 	onValueChange: mapType => navigation.setParams({ mapType })
	// };
	// const headerRight = () => (
	// 	<Picker {...headerRightProps}>
	// 		<Picker.Item label="普通" value={'normal'}/>		
	// 		<Picker.Item label="卫星" value={'satellite'}/>		
	// 		<Picker.Item label="夜间" value={'night'}/>		
	// 		<Picker.Item label="导航" value={'navi'}/>		
	// 		<Picker.Item label="公交" value={'bus'}/>		
	// 	</Picker>
	// );

	// navigation.setOptions({ headerRight });


	return (
		<View style={{flex: 1}}>
			<GDMapView style={{flex: 1}}
				mapType={mapType}/>
		</View>
	);
};
