<template>
  <uni-popup ref="uniPopup" type="bottom">
    <view class="content">
      <text class="mix-icon icon-guanbi" @click="close"></text>
      <view class="center title">
        <text>输入支付密码</text>
      </view>
      <view class="input center">
        <view class="item center" :class="{has: pwd.length > index}" v-for="(item, index) in 6" :key="index"></view>
      </view>
      <view class="reset-btn center" @click="navTo('/pages/auth/payPassword')">
        <text>重置密码</text>
      </view>
      <number-keyboard ref="keybord" @onChange="onNumberChange"></number-keyboard>
    </view>
  </uni-popup>
</template>

<script>
/**
 * 支付密码键盘
 */
export default {
  data() {
    return {
      pwd: ''
    };
  },
  watch: {
    pwd(pwd) {
      if (pwd.length === 0) {
        this.$refs.keybord.val = '';
      }
    }
  },
  methods: {
    open() {
      this.$refs.uniPopup.open();
    },
    close() {
      this.$refs.uniPopup.close();
    },
    onNumberChange(pwd) {
      this.pwd = pwd;
      if (pwd.length >= 6) {
        this.$emit('onConfirm', pwd.substring(0, 6));
      }
    }
  }
}
</script>

<style scoped lang="scss">
.content {
  border-radius: 20 rpx 20 rpx 0 0;
  background-color: #fff;
  position: relative;
}

.title {
  height: 110 rpx;
  font-size: 32 rpx;
  color: #333;
  font-weight: 700;
}

.input {
  padding: 30 rpx 0 60 rpx;

  .item {
    width: 88 rpx;
    height: 88 rpx;
    margin: 0 10 rpx;
    border: 1px solid #ddd;
    border-radius: 4 rpx;
  }

  .has:after {
    content: '';
    width: 16 rpx;
    height: 16 rpx;
    border-radius: 100 rpx;
    background-color: #333;
  }
}

.reset-btn {
  padding-bottom: 20 rpx;
  margin-top: -10rpx;
  margin-bottom: 30 rpx;
  font-size: 28 rpx;
  color: #007aff;
}

.icon-guanbi {
  position: absolute;
  left: 10 rpx;
  top: 24 rpx;
  padding: 20 rpx;
  font-size: 28 rpx;
}
</style>
