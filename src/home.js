import React from 'react';
import {
	View,
	ScrollView
} from 'react-native';
import { ListItem, Text } from 'react-native-elements'

export default Home = (props) => {

	const { navigation } = props;

	const list = [
		{
			name: '显示地图',
			mark: '基础地图'
		},
	];

	return (
		<ScrollView style={{ flex: 1 }}>
		{
			list.map((c, i) => {
				console.log(c.mark);
				return c.mark ? (
					<View 
						style={{flexDirection: 'column'}}
						key={i}>
						<Text h4
							style={{margin: 10}}>{c.mark}</Text>
						<ListItem 
							title={c.name}
							topDivider
							bottomDivider
							onPress={() => {
								navigation.navigate(c.name);
							}}/>
					</View>
				) : (
					<ListItem 
						key={i}
						title={c.name}
						topDivider
						bottomDivider
						onPress={() => {
							navigation.navigate(c.name);
						}}/>
				);
			})
		}
		</ScrollView>
	);
};
