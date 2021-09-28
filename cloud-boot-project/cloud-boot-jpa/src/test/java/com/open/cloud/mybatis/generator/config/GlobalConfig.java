package com.open.cloud.mybatis.generator.config;


/**
 * 全局配置
 *
 * @author hubin
 * @since 2016-12-02
 */
public class GlobalConfig {

    /**
     * 生成文件的输出目录【 windows:D://  linux or mac:/tmp 】
     */
    private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";
    /**
     * 是否覆盖已有文件（默认 false）
     */
    private boolean fileOverride;
    /**
     * 作者
     */
    private String author = "作者";

    private GlobalConfig() {
    }

    public String getOutputDir() {
        return outputDir;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }


    public String getAuthor() {
        return author;
    }


    public static final class GlobalConfigBuilder {
        private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";
        private boolean fileOverride;
        private String author = "作者";

        private GlobalConfigBuilder() {
        }

        public static GlobalConfigBuilder GlobalConfig() {
            return new GlobalConfigBuilder();
        }

        public GlobalConfigBuilder withOutputDir(String outputDir) {
            this.outputDir = outputDir;
            return this;
        }

        public GlobalConfigBuilder withFileOverride(boolean fileOverride) {
            this.fileOverride = fileOverride;
            return this;
        }

        public GlobalConfigBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public GlobalConfig build() {
            GlobalConfig globalConfig = new GlobalConfig();
            globalConfig.outputDir = this.outputDir;
            globalConfig.fileOverride = this.fileOverride;
            globalConfig.author = this.author;
            return globalConfig;
        }
    }
}
