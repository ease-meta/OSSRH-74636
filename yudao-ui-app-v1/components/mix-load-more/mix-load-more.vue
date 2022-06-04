<template>
  <view>
    <view v-show="status !== 2" class="mix-load-more center">
      <image class="loading-icon" src="/static/loading/hamster.gif"></image>
      <text class="text">{{ textList[status] }}</text>
    </view>
    <view v-show="status === 2" class="mix-load-more center">
      <image class="logo" src="/static/logo-b-w.png"></image>
      <text class="text">国云网络提供技术支持</text>
    </view>
  </view>
</template>

<script>
/**
 * 上划加载更多
 * @prop {Number} status 0加载前，1加载中，2没有更多
 * @prop {Array} textList ['加载前提示', '加载中提示', '加载完提示']
 */
export default {
  name: "mix-load-more",
  props: {
    status: {
      type: Number,
      default: 0
    },
    textList: {
      type: Array,
      default() {
        return [
          '上拉显示更多',
          '正在加载 ..',
          '我也是有底线的~'
        ];
      }
    }
  }
}
</script>

<style scoped lang="scss">
.mix-load-more {
  width: 750 rpx;
  height: 110 rpx;
}

.loading-icon {
  width: 64 rpx;
  height: 68 rpx;
  margin-right: 20 rpx;
}

.text {
  font-size: 26 rpx;
  color: #999;
}

.logo {
  width: 34 rpx;
  height: 34 rpx;
  margin-right: 12 rpx;
}
</style>