package com.fincatto.documentofiscal.validadores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

/**
 * Validacao de uma NF-e 4.00 minima contra o pacote de schemas PL_010g_v1.03 (NT 2026.008).
 */
public class DFXMLValidadorNFe400Test {

    private static final String PRODUTO_SEM_LIQUIDO = "<vProd>200.00</vProd><cEANTrib>";
    private static final String GRUPO_ICMS00 = "<ICMS><ICMS00><orig>0</orig><CST>00</CST><modBC>3</modBC><vBC>200.00</vBC><pICMS>17.00</pICMS><vICMS>34.00</vICMS></ICMS00></ICMS>";
    private static final String INICIO_TOTAL = "<total><ICMSTot>";

    private static final String NFE = "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe versao=\"4.00\" Id=\"NFe43261112345678000195550010000000011000000014\">"
            + "<ide><cUF>43</cUF><cNF>00000001</cNF><natOp>VENDA</natOp><mod>55</mod><serie>1</serie><nNF>1</nNF><dhEmi>2026-11-06T10:00:00-03:00</dhEmi><tpNF>1</tpNF><idDest>1</idDest><cMunFG>4314902</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>4</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>1</indFinal><indPres>1</indPres><procEmi>0</procEmi><verProc>1.0</verProc></ide>"
            + "<emit><CNPJ>12345678000195</CNPJ><xNome>EMITENTE TESTE</xNome><enderEmit><xLgr>RUA A</xLgr><nro>1</nro><xBairro>CENTRO</xBairro><cMun>4314902</cMun><xMun>PORTO ALEGRE</xMun><UF>RS</UF><CEP>90000000</CEP></enderEmit><IE>1234567890</IE><CRT>3</CRT></emit>"
            + "<det nItem=\"1\"><prod><cProd>1</cProd><cEAN>SEM GTIN</cEAN><xProd>PRODUTO TESTE</xProd><NCM>27111910</NCM><CFOP>5102</CFOP><uCom>UN</uCom><qCom>2.0000</qCom><vUnCom>100.00</vUnCom>" + PRODUTO_SEM_LIQUIDO + "SEM GTIN</cEANTrib><uTrib>UN</uTrib><qTrib>2.0000</qTrib><vUnTrib>100.00</vUnTrib><indTot>1</indTot></prod>"
            + "<imposto>" + GRUPO_ICMS00 + "</imposto></det>"
            + INICIO_TOTAL + "<vBC>200.00</vBC><vICMS>34.00</vICMS><vICMSDeson>0.00</vICMSDeson><vFCP>0.00</vFCP><vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet><vProd>200.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>200.00</vNF></ICMSTot></total>"
            + "<transp><modFrete>9</modFrete></transp><pag><detPag><tPag>01</tPag><vPag>200.00</vPag></detPag></pag></infNFe>"
            + "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe43261112345678000195550010000000011000000014\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>DGElW+22IC8ksSISecob4b2l5QU=</DigestValue></Reference></SignedInfo>"
            + "<SignatureValue>aGtkSUxYZUVWLzJ4TnBqREVNTFI2VVNaWVFGL3o5V3BIRUZYVlZ3cnZQQXZ1VnJSOXpwdXVMQTcz</SignatureValue><KeyInfo><X509Data><X509Certificate>TUlJSDZEQ0NCZENnQXdJQkFnSVFkYzF2bDNoUFhVQ25tdmdBdm53MGZEQU5CZ2txaGtpRzl3MEJBUXNGQURCNE1Rc3dDUVlEVlFRRw==</X509Certificate></X509Data></KeyInfo></Signature></NFe>";

    @Test
    public void deveValidarNotaSemOsCamposDaNT2026008() throws Exception {
        Assertions.assertTrue(DFXMLValidador.validaNota400(NFE));
    }

    @Test
    public void deveValidarNotaComValoresLiquidosNoItemENoTotal() throws Exception {
        final String xml = NFE
                .replace(PRODUTO_SEM_LIQUIDO, "<vProd>200.00</vProd><vUnComLiq>83.0000000001</vUnComLiq><vProdLiq>166.00</vProdLiq><cEANTrib>")
                .replace(INICIO_TOTAL, "<total><vProdLiqTot>166.00</vProdLiqTot><ICMSTot>");
        Assertions.assertTrue(DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void deveValidarNotaComGrupoIcmsPrevistoNoPagamentoAntecipado() throws Exception {
        final String xml = NFE.replace(GRUPO_ICMS00, "<ICMS><ICMSPrevistoPagtoAntecip><vICMSPrevisto>0.00</vICMSPrevisto></ICMSPrevistoPagtoAntecip></ICMS>");
        Assertions.assertTrue(DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarValorLiquidoUnitarioSemValorLiquidoDoProduto() {
        final String xml = NFE.replace(PRODUTO_SEM_LIQUIDO, "<vProd>200.00</vProd><vUnComLiq>83.00</vUnComLiq><cEANTrib>");
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarGrupoIcmsPrevistoJuntoComGrupoDoCst() {
        final String xml = NFE.replace(GRUPO_ICMS00, GRUPO_ICMS00.replace("</ICMS00></ICMS>", "</ICMS00><ICMSPrevistoPagtoAntecip><vICMSPrevisto>0.00</vICMSPrevisto></ICMSPrevistoPagtoAntecip></ICMS>"));
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    // NT 2026.005 (Simples Nacional) - XSDs provisorios incluidos manualmente no PL_010g_v1.03
    private static final String FIM_EMITENTE = "<CRT>3</CRT></emit>";
    private static final String FIM_PRODUTO = "<indTot>1</indTot></prod>";
    private static final String FIM_IMPOSTO = "</ICMS></imposto>";
    private static final String FIM_TOTAL = "</ICMSTot></total>";
    private static final String GRUPO_TRIB_SN_ITEM = "<gTribSN><gIBSSN><pIBSSN>1.2345</pIBSSN><vIBSSN>2.47</vIBSSN></gIBSSN><gCBSSN><pCBSSN>3.50</pCBSSN><vCBSSN>7.00</vCBSSN></gCBSSN></gTribSN>";
    private static final String GRUPO_TRIB_SN_TOTAL = "<gTribSN><vIBSSN>2.47</vIBSSN><vCBSSN>7.00</vCBSSN></gTribSN>";

    private static String notaSimplesNacional(final String emitente, final String produto, final String ibscbsItem, final String ibscbsTotal) {
        return NFE.replace(FIM_EMITENTE, emitente)
                .replace(FIM_PRODUTO, produto)
                .replace(FIM_IMPOSTO, "</ICMS><IBSCBS><CST>410</CST><cClassTrib>410001</cClassTrib>" + ibscbsItem + "</IBSCBS></imposto>")
                .replace(FIM_TOTAL, "</ICMSTot><IBSCBSTot><vBCIBSCBS>0.00</vBCIBSCBS>" + ibscbsTotal + "</IBSCBSTot></total>");
    }

    @Test
    public void deveValidarNotaComOsCamposDaNT2026005() throws Exception {
        final String xml = notaSimplesNacional("<CRT>3</CRT><tpRegimeIBSCBS>2</tpRegimeIBSCBS></emit>", "<indTot>1</indTot><cAtivSN>01</cAtivSN></prod>", GRUPO_TRIB_SN_ITEM, GRUPO_TRIB_SN_TOTAL);
        Assertions.assertTrue(DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void deveValidarNotaComGrupoTribSNSemGrupoIBSSN() throws Exception {
        final String xml = notaSimplesNacional("<CRT>2</CRT></emit>", "<indTot>1</indTot><cAtivSN>90</cAtivSN></prod>", "<gTribSN><gCBSSN><pCBSSN>3.50</pCBSSN><vCBSSN>7.00</vCBSSN></gCBSSN></gTribSN>", "<gTribSN><vIBSSN>0.00</vIBSSN><vCBSSN>7.00</vCBSSN></gTribSN>");
        Assertions.assertTrue(DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void deveValidarGrupoTribSNAposEstornoDeCreditoNoItemENoTotal() throws Exception {
        final String xml = notaSimplesNacional(FIM_EMITENTE, FIM_PRODUTO,
                "<gEstornoCred><vIBSEstCred>1.00</vIBSEstCred><vCBSEstCred>1.00</vCBSEstCred></gEstornoCred>" + GRUPO_TRIB_SN_ITEM,
                "<gEstornoCred><vIBSEstCred>1.00</vIBSEstCred><vCBSEstCred>1.00</vCBSEstCred></gEstornoCred>" + GRUPO_TRIB_SN_TOTAL);
        Assertions.assertTrue(DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarTipoRegimeIBSCBSAntesDoCRT() {
        final String xml = NFE.replace(FIM_EMITENTE, "<tpRegimeIBSCBS>1</tpRegimeIBSCBS><CRT>1</CRT></emit>");
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarTipoRegimeIBSCBSInexistente() {
        final String xml = NFE.replace(FIM_EMITENTE, "<CRT>1</CRT><tpRegimeIBSCBS>5</tpRegimeIBSCBS></emit>");
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarCodigoAtividadeSimplesNacionalAntesDoIndicadorTotal() {
        final String xml = NFE.replace(FIM_PRODUTO, "<cAtivSN>01</cAtivSN><indTot>1</indTot></prod>");
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarCodigoAtividadeSimplesNacionalInexistente() {
        final String xml = NFE.replace(FIM_PRODUTO, "<indTot>1</indTot><cAtivSN>1</cAtivSN></prod>");
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarGrupoTribSNSemGrupoCBSSN() {
        final String xml = notaSimplesNacional(FIM_EMITENTE, FIM_PRODUTO, "<gTribSN><gIBSSN><pIBSSN>1.2345</pIBSSN><vIBSSN>2.47</vIBSSN></gIBSSN></gTribSN>", GRUPO_TRIB_SN_TOTAL);
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarGrupoTribSNAntesDoEstornoDeCreditoNoItem() {
        final String xml = notaSimplesNacional(FIM_EMITENTE, FIM_PRODUTO, GRUPO_TRIB_SN_ITEM + "<gEstornoCred><vIBSEstCred>1.00</vIBSEstCred><vCBSEstCred>1.00</vCBSEstCred></gEstornoCred>", GRUPO_TRIB_SN_TOTAL);
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarGrupoTribSNAntesDoEstornoDeCreditoNoTotal() {
        final String xml = notaSimplesNacional(FIM_EMITENTE, FIM_PRODUTO, GRUPO_TRIB_SN_ITEM, GRUPO_TRIB_SN_TOTAL + "<gEstornoCred><vIBSEstCred>1.00</vIBSEstCred><vCBSEstCred>1.00</vCBSEstCred></gEstornoCred>");
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarGrupoCBSSNAntesDoGrupoIBSSN() {
        final String xml = notaSimplesNacional(FIM_EMITENTE, FIM_PRODUTO, "<gTribSN><gCBSSN><pCBSSN>3.50</pCBSSN><vCBSSN>7.00</vCBSSN></gCBSSN><gIBSSN><pIBSSN>1.2345</pIBSSN><vIBSSN>2.47</vIBSSN></gIBSSN></gTribSN>", GRUPO_TRIB_SN_TOTAL);
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }

    @Test
    public void naoDeveValidarAliquotaIBSSNComMaisDeQuatroCasasDecimais() {
        final String xml = notaSimplesNacional(FIM_EMITENTE, FIM_PRODUTO, GRUPO_TRIB_SN_ITEM.replace("1.2345", "1.23456"), GRUPO_TRIB_SN_TOTAL);
        Assertions.assertThrows(SAXParseException.class, () -> DFXMLValidador.validaNota400(xml));
    }
}
