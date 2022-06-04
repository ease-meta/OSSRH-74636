<template>
  <uni-popup ref="popup">
    <view class="pop-content">
      <text class="title">{{ title }}</text>
      <view class="con center">
        <text class="text">{{ text }}</text>
      </view>
      <view class="btn-group row b-t">
        <view class="btn center" @click="close">
          <text>{{ cancelText }}</text>
        </view>
        <view class="btn center b-l" @click="confirm">
          <text>{{ confirmText }}</text>
        </view>
      </view>
    </view>
  </uni-popup>
</template>

<script>
/**
 * 确认对话框
 * @prop title 标题
 * @prop text 提示内容
 * @prop cancelText 取消按钮文字
 * @prop confirmText 确认按钮文字
 * @event onConfirm 确认按钮点击时触发
 */
export default {
  data() {
    return {};
  },
  props: {
    title: String,
    text: String,
    cancelText: {
      type: String,
      default: '取消'
    },
    confirmText: {
      type: String,
      default: '确定'
    }
  },
  methods: {
    confirm() {
      this.$emit('onConfirm');
      this.close();
    },
    open() {
      this.$refs.popup.open();
    },
    close() {
      this.$refs.popup.close();
    }
  }
}
</script>

<style scoped lang='scss'>
.pop-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 540 rpx;
  padding-top: 36 rpx;
  background-color: #fff;
  border-radius: 24 rpx;
  overflow: hidden;

  .title {
    font-size: 32 rpx;
    color: #333;
    line-height: 48 rpx;
    font-weight: 700;
  }

  .con {
    padding: 36 rpx 40 rpx 54 rpx;
  }

  .text {
    width: 460 rpx;
    font-size: 26 rpx;
    color: #333;
    line-height: 40 rpx;
    text-align: center;
  }

  .btn-group {
    width: 100%;
  }

  .btn {
    flex: 1;
    height: 88 rpx;
    line-height: 88 rpx;
    font-size: 32 rpx;
    color: #777;

    &:last-child {
      color: #007aff;
    }
  }
}
</style>
