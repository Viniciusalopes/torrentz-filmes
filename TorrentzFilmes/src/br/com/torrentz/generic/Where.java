/*
 *  ----------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Viniciusalopes (Vovolinux) <suporte@vovolinux.com.br>
 *  Criado em  : 22/10/2020 04:41:47 
 *  Projeto    : API Acervo Musical
 *  Versão     : 0.1.0
 *  ------------------------------------------------------------------------------------------------
 *  Propósito  : Modelo de classe para Fields de CRUDs.
 *  ------------------------------------------------------------------------------------------------
 *  Changelog:
 *  Autor      : NOME DO AUTORNOME DO AUTOR
 *  Data       : YYYY-mm-dd
 *  Versão     : n.n.n
 *  Alterações : DUPLICAR ESTE BLOCO E DESCREVER A(s) ALTERAÇÃO(ões) RELEVANTES PARA A VERSÃO.
 *               MANTER INDENTAÇÃO DE LINHAS ABAIXO DA PRIMEIRA LINHA E O LIMITE DE COLUNAS AQUI -->
 *  -----------------------------------------------------------------------------------------------| 
 */
package br.com.torrentz.generic;

import java.util.Date;

/**
 *
 * @author vovostudio
 */
public class Where {

    //--- ATRIBUTOS ------------------------------------------------------------------------------->
    //
    private String preField = "";
    private String fieldName = "";
    private String comparer = "";
    private int intValue = 0;
    private float floatValue = 0;
    private double doubleValue = 0.0;
    private String strValue = "";
    private Date dateValue = null;
    private Object objValue = null;

    //--- FIM ATRIBUTOS ---------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES ---------------------------------------------------------------------------->
    //
    public Where() {

    }

    private Where(String preField, String fieldName, String comparer){
        this.preField = preField;
        this.fieldName = fieldName;
        this.comparer = comparer;
    }
    public Where(String preField, String fieldName, String comparer, int value) {
        this(preField, fieldName, comparer);
        this.intValue = value;
        this.objValue = value;
    }

    public Where(String preField, String fieldName, String comparer, float value) {
        this(preField, fieldName, comparer);
        this.floatValue = value;
        this.objValue = value;
    }

    public Where(String preField, String fieldName, String comparer, double value) {
        this(preField, fieldName, comparer);
        this.doubleValue = value;
        this.objValue = value;
    }

    public Where(String preField, String fieldName, String comparer, String value) {
        this(preField, fieldName, comparer);
        this.strValue = value;
        this.objValue = value;
    }

    public Where(String preField, String fieldName, String comparer, Date value) {
        this(preField, fieldName, comparer);
        this.dateValue = value;
        this.objValue = value;
    }

    public Where(String preField, String fieldName, String comparer, Object value) {
        this(preField, fieldName, comparer);
        this.objValue = value;
    }

    //--- FIM CONSTRUTORES ------------------------------------------------------------------------|
    //
    //--- GET ------------------------------------------------------------------------------------->
    //
    public String getPreField() {
        return preField;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getComparer() {
        return comparer;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public Object getObjValue() {
        return objValue;
    }

    //--- FIM GET ---------------------------------------------------------------------------------|
    //
    //--- SET ------------------------------------------------------------------------------------->
    //
    public void setPreField(String preField) {
        this.preField = preField;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setComparer(String comparer) {
        this.comparer = comparer;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public void setObjValue(Object objValue) {
        this.objValue = objValue;

        String classObjValue = objValue.getClass().getCanonicalName();

        switch (classObjValue) {
            case "Integer":
                this.intValue = (int) objValue;
                break;
            case "Float":
                this.floatValue = (float) objValue;
                break;
            case "Double":
                this.doubleValue = (double) objValue;
                break;
            case "String":
                this.strValue = objValue + "";
                break;
            case "Date":
                this.dateValue = (Date) objValue;
                break;
        }
    }
    //--- FIM SET ---------------------------------------------------------------------------------|
    //
}
