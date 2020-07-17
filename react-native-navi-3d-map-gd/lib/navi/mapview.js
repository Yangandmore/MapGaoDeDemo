import React from 'react';
import SuperComponent from '../supercomponent.js'
import { View, requireNativeComponent, ViewPropTypes, UIManager, findNodeHandle } from 'react-native';
import PropTypes from 'prop-types'
import PropMapType from '../proptype'

export default class GDMapView extends SuperComponent {

	name = "GDMapView";

	static defaultProps = {
		mapType: 'normal',
	}

	static propTypes = {
		...ViewPropTypes,

		/*
		 * 地图类型
		 * - normal：标准地图
		 * - satellite：卫星地图
		 * - night：夜间地图
		 * - navi：导航地图
		 * - bus：公交地图
		 */
		mapType: PropTypes.oneOf(['normal', 'satellite', 'night', 'navi', 'bus']),

		/*
		 * 是否定位
		 */
		locationEnable: PropTypes.bool,

		/* 定位模式(android)
		 * - show：只定位一次
		 * - locate：定位一次，且将视角移动到地图中心
		 * - follow：连续定位，且将视角移动到地图中心，且跟随设备移动(1秒1次)
		 * - map_rotate：连续定位，且将视角移动到地图中心，地图依照设备方向旋转，跟随移动(1秒1次)
		 * - location_rotate：连续定位，且将视角移动到地图中心，定位点依照设备方向旋转，跟随移动(1秒1次)(默认模式)
		 * - location_rotate_no_center：连续定位，蓝点不会移动到地图中心，定位点依照设备方向旋转，且蓝点跟随移动
		 * - follow_no_center：连续定位，蓝点不会移动到地图中心，蓝点跟随设备移动
		 * - map_rotate_no_center：连续定位，蓝点不会移动到地图中心，地图依照设备方向旋转，蓝点跟随设备移动
		 */
		locationTypeAndroid: PropTypes.oneOf([
			'show',
			'locate',
			'follow',
			'map_rotate',
			'location_rotate',
			'location_rotate_no_center',
			'follow_no_center',
			'map_rotate_no_center'
		]),

		/* 定位模式(ios)
		 * - none：不追踪用户的location更新(默认模式)
		 * - follow：追踪用户的location更新
		 * - follow_with_heading：追踪用户的location与heading更新，及跟随手机移动地图
		 */
		locationTypeIos: PropTypes.oneOf([
			'none',
			'follow',
			'follow_with_heading'
		]),

		/*
		 * location配置
		 */
		locationStyle: PropMapType.LocationStyle,

		/*
		 * 定位时间频率
		 * Android
		 */
		locationInterval: PropTypes.number,

    /**
     * 是否显示室内地图
     */
    showsIndoorMap: PropTypes.bool,

    /**
     * 是否显示3D建筑
     */
    showsBuildings: PropTypes.bool,

    /**
     * 是否显示文本标签
     */
    showsLabels: PropTypes.bool,

    /**
     * 是否显示路况
     */
    showsTraffic: PropTypes.bool,

		/**
		 * 显示缩放按钮
		 * Android
		 */
		showsZoomControls: PropTypes.bool,

		/**
		 * 显示指南针
		 */
		showsCompass: PropTypes.bool,

		/**
		 * 显示定位按钮
		 * Android
		 */
		showsLocationButton: PropTypes.bool,

		/**
		 * 显示比例尺
		 */
		showsScale: PropTypes.bool,

		/**
		 * 是否缩放
		 */
		zoomEnabled: PropTypes.bool,

		/**
		 * 是否滑动
		 */
		scrollEnabled: PropTypes.bool,

		/**
		 * 是否旋转
		 */
		rotateEnabled: PropTypes.bool,

		/**
		 * 是否倾斜
		 */
		tiltEnabled: PropTypes.bool,

		/**
		 * 缩放等级
		 * [3, 19]
		 */
		zoomLevel: PropTypes.number,
		
		/**
		 * 滑动中心点
		 */
		centerCoordinates: PropMapType.LatLng, 

		/**
		 * 旋转角度
		 * [0, 360]
		 */
		rotation: PropTypes.number,

		/**
		 * 倾斜角度
		 * [0, 45]
		 */
		tilt: PropTypes.number,

    /**
     * 显示区域
     */
    region: PropMapType.LatLngBounds,

    /**
     * 限制地图只能显示某个矩形区域, 针对需要展示部分固定范围的地图
     */
    limitRegion: PropMapType.LatLngBounds,

		/**
		 * 截屏回调
		 * {{ nativeEvent: {
		 * 	isSuccess
		 * 	isRender
		 * 	path
		 * } }}
		 */
		onMapScreenShot: PropTypes.func,

		/**
		 * 点击事件
		 * {{ nativeEvent: {
		 * 	latitude
		 * 	longitude
		 * } }}
		 */
		onPress: PropTypes.func,

		/**
		 * 长按事件
		 * {{ nativeEvent: {
		 * 	latitude
		 * 	longitude
		 * } }}
		 */
		onLongPress: PropTypes.func,

		/**
		 * 定位事件
		 */
		onLocation: PropTypes.func,

		/**
		 * 动画回调
		 * {{ nativeEvent: {
		 * 	status
		 * } }}
		 */
		onAnimateComplete: PropTypes.func,

		/**
		 * 地图状态改变事件
		 * {{ nativeEvent: {
		 * 	zoomLevel
		 * 	tilt
		 * 	rotation
		 * 	centerLatitude
		 * 	centerLongitude
		 * } }}
		 */
		onStatusChange: PropTypes.func,

		/**
		 * 地图状态改变结束事件
		 * {{ nativeEvent: {
		 * 	zoomLevel
		 * 	tilt
		 * 	rotation
		 * 	centerLatitude
		 * 	centerLongitude
		 * 	spanLatitude
		 * 	spanLongitude
		 * } }}
		 */
		onStatusChangeComplete: PropTypes.func

	}

	// 中英文地图
	// 中文 CN
	// 英文 EN
	setMapLanguage = (language) => {
		this.sendCommand('GDMapView', 'setMapLanguage', [
			language
		]);
	}

	// 设置范围
	setRegion = (region) => {
			this.sendCommand('GDMapView', 'setRegion', [
			region
		]);
	}

	// 设置限制范围
	setLimitRegion = (limitRegion) => {
		this.sendCommand('GDMapView', 'setLimitRegion', [
			limitRegion
		]);
	}

	// 截屏操作
	setScreenShot = () => {
		this.sendCommand('GDMapView', 'setScreenShot', []);
	}

	// 动画跳跃
	setAnimateTo = (locationData, time) => {
		this.sendCommand('GDMapView', 'setAnimateTo', [
			locationData,
			time
		]);
	}

	render() {
		const props = { ...this.props };
		return (
			<MapView {...props}/>
		);
	}
};

const MapView = requireNativeComponent('GDMapView', GDMapView);
