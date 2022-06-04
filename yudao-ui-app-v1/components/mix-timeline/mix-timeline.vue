<template>
  <view class="mix-timeline">
    <view class="cell" v-for="(item, index) in list" :key="index">
      <view class="left column center">
        <text class="time">{{ item.time | date('H:i') }}</text>
        <text class="date">{{ item.time | date('m/d') }}</text>
      </view>
      <view class="cen center">
        <view class="circle center"></view>
      </view>
      <view class="right column">
        <text class="title">{{ item.title }}</text>
        <text v-if="item.tip" class="tip">{{ item.tip }}</text>
      </view>
    </view>
  </view>
</template>

<script>
/**
 * 时间轴
 * {
 *	title: 标题
 *	tip: 小字
 *	time: 时间戳
 *	}
 */
export default {
  data() {
    return {};
  },
  props: {
    list: {
      type: Array,
      default() {
        return []
      }
    }
  }
}
</script>

<style scoped lang="scss">
.mix-timeline {

}

.cell {
  display: flex;
  align-items: flex-start;
  width: 100%;
  padding: 0 30 rpx 0;

  &:first-child .circle {
    &:before {
      background-color: $base-color;
    }

    &:after {
      content: '';
      position: absolute;
      width: 28 rpx;
      height: 28 rpx;
      background-color: #f9e0eb;
      border-radius: 100 rpx;
    }
  }

  &:last-child .right:before {
    display: none;
  }
}

.left {

  .time {
    font-size: 26 rpx;
    color: #333;
    line-height: 44 rpx;
  }

  .date {
    font-size: 20 rpx;
    color: #333;
  }
}

.cen {
  width: 80 rpx;
  height: 44 rpx;

  .circle {
    width: 16 rpx;
    height: 16 rpx;
    position: relative;
    z-index: 1;

    &:before {
      content: '';
      width: 16 rpx;
      height: 16 rpx;
      background-color: #ddd;
      border-radius: 100 rpx;
      position: relative;
      z-index: 5;
    }
  }
}

.right {
  flex: 1;
  padding-bottom: 30 rpx;
  position: relative;
  min-height: 96 rpx;

  &:before {
    content: '';
    width: 2 rpx;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    background-color: #ddd;
    transform: translate(-42rpx, 22 rpx);
  }

  .title {
    font-size: 28 rpx;
    color: #333;
    line-height: 44 rpx;
    font-weight: 700;
  }

  .tip {
    margin-top: 6 rpx;
    font-size: 24 rpx;
    color: #999;
    line-height: 36 rpx;
  }
}


</style>
