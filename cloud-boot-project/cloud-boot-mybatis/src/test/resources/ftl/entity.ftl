package ${mouldName}.${entityPackage};

import ${parentClassPackage}.${entityParentClass};
<#if tablePkSize =="Y">
  import com.dcits.comet.dao.annotation.TablePk;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @Author ${author}
* @Description ${functionComment}
* @Date ${date}
* @Version 1.0
*/

@Data
@EqualsAndHashCode(callSuper = false)
<#if tableType ??>
  @TableType(name = "${tableName}", value = TableTypeEnum.${tableType})
</#if>
public class ${className} extends ${entityParentClass} {

<#list entityList as c>
  /**
  * This field corresponds to the database column ${tableName}.${ c.columnNameL}
  *
  * @Description ${ c.columnComment}
  */
    <#if c.partitonKey ??>
        ${c.partitonKey}
    </#if>
    <#if c.cloumsTop ??>
        ${c.cloumsTop}
    </#if>
  private ${c.javaType} ${ c.columnName};

</#list>
}
