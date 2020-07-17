import PropTypes from 'prop-types';

// 颜色要求必须是 #xxxxxx & #xxxxxxxx 格式
const LocationStyle = PropTypes.shape({
	// 蓝点自定义图标
	locationIcon: PropTypes.string,
	// Android
	locationAnchorX: PropTypes.number,
	// 蓝点图标锚点纬度
	// Android
	locationAnchorY: PropTypes.number,
	// 是否显示精度圈
	showsAccuracyRing: PropTypes.bool,
	// 蓝点精度圈的边框颜色
	strokeColor: PropTypes.string,
	// 蓝点精度圈的填充颜色
	radiusFillColor: PropTypes.string,
	// 蓝点精度圈的边框宽度
	strokeWidth: PropTypes.number,
});

const LatLng = PropTypes.shape({
	// 纬度
	latitude: PropTypes.number,
	// 经度
	longitude: PropTypes.number
});

const LatLngBounds = PropTypes.shape({
	// 中心点位
	// 纬度
	centerLatitude: PropTypes.number,
	// 经度
	centerLongitude: PropTypes.number,

	// 矩形跨度
	spanLatitude: PropTypes.number,
	spanLongitude: PropTypes.number
});

const LocationAnchor = PropTypes.shape({
	// Android
	locationAnchorX: PropTypes.number,
	// 蓝点图标锚点纬度
	// Android
	locationAnchorY: PropTypes.number,
});

export default {
	LocationStyle,
	LatLng,
	LatLngBounds,
	LocationAnchor
};
