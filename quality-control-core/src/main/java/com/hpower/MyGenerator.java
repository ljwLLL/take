package com.hpower;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.hpower.util.MybatisPlusUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 数据库表，代码生成器
 *
 * @author yangyang.jiang
 * @date 2020/03/23
 * @since 1.0
 */
public class MyGenerator {
    /**
     * 数据库地址
     */
    String dbUrl = "jdbc:mysql://127.0.0.1:3306/quality_control";
    String userName = "root";
    String Password = "abc123";

    /**
     * 是否强制带上注解
     */
    boolean enableTableFieldAnnotation = true;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    String[] fieldPrefix = new String[]{"t_"};

    private void generateByTablesWithInjectConfig(String authorName, String[] excludeTableNames, String... tableNames) {
        File file = new File("");
        String path = file.getAbsolutePath();
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        // 开启 activeRecord 模式
        config.setActiveRecord(true)
                //生成目录
                .setOutputDir(path + "/src/main/java")
                //生成人
                .setAuthor(authorName)
                // 是否覆盖文件
                .setFileOverride(true)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                .setOpen(false)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl");

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(userName)
                .setPassword(Password)
                .setDriverName("com.mysql.jdbc.Driver");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setVersionFieldName("version")
                .setLogicDeleteFieldName("valid")
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setEntityColumnConstant(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .entityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setTablePrefix(fieldPrefix)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.hpower")
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mapper");

        //模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);

        // 代码生成器
        MybatisPlusUtil autoGenerator = new MybatisPlusUtil();
        autoGenerator.setExcludeArrays(excludeTableNames);
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplate(templateConfig)
                .execute();
    }

    @Test
    public void generateCodeWithInjectConfig() {
        //generateByTablesWithInjectConfig("yangyang.jiang", null, "^[a-zA-Z_]+$");
        generateByTablesWithInjectConfig("yangyang.jiang", null, "t_hospital_year_process");
    }
}
