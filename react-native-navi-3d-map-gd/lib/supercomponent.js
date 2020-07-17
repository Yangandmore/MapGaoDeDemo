import React from 'react';
import { requireNativeComponent, UIManager, findNodeHandle } from 'react-native';

export default class SuperComponent extends React.PureComponent {
	name;

	/**
	 * 调用原生控件函数
	 */
	sendCommand = (viewName, funcName, data = []) => {
		UIManager.dispatchViewManagerCommand(
			findNodeHandle(this),
			UIManager.getViewManagerConfig(viewName).Commands[funcName],
			data
		)
	};

}
