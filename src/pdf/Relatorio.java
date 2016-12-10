/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import annotations.Coluna;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author guilherme
 */
public class Relatorio {
    private String nomeArquivo;
    private Document document;
    private Chunk  titulo;
    private Chunk subTitulo;
    private PdfPTable tabela;
    
    public Relatorio(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;    
        this.titulo = new Chunk("");
        this.titulo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 30f));

        this.subTitulo = new Chunk("");
        this.subTitulo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 14f));
    }
    
    public Relatorio(String nomeArquivo, String titulo) {
        this(nomeArquivo);
        this.titulo = new Chunk(titulo);
        this.titulo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 30f));
    } 
    public Relatorio(String nomeArquivo, String titulo, String subtitulo) {
        this(nomeArquivo, titulo);
        this.subTitulo = new Chunk(subtitulo);
        this.subTitulo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 14f));
    }
    
    public void setSubtitulo(String subtitulo) {
        this.subTitulo = new Chunk(titulo);
        this.subTitulo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 14f));
    }
    
    public void setTitulo(String titulo) {
        this.titulo = new Chunk(titulo);
        this.titulo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 30f));
    }
    
    public void geraDocumento() {
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));

            document.open(); 

            document.add(new Paragraph(titulo));
            document.add(new Paragraph(subTitulo));
            document.add(Chunk.NEWLINE);
            document.add(tabela);

            document.close();
        } catch(DocumentException e) {
            
        } catch(FileNotFoundException e) {
            
        }
    }
    
    public <T> void adicionaElementosNaTabela(List<T> listaDeElementos) {
        Class classeElemento = listaDeElementos.get(0).getClass();
        Map<Integer, String> colunas = new TreeMap<>();
  
        for(Method metodo : classeElemento.getDeclaredMethods()) {
            if(metodo.isAnnotationPresent(Coluna.class)) {
                Coluna anotacao = metodo.getAnnotation(Coluna.class);
                colunas.put(anotacao.posicao(), anotacao.nome());
            }
        }
        
        tabela = new PdfPTable(colunas.keySet().size());
        tabela.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
        
        for(int i=0; i < colunas.keySet().size(); i++) {
            PdfPCell celula = new PdfPCell(new Phrase(colunas.get(i)));
            tabela.addCell(celula);
        }
        
        int j = 0, k = 0;
        for(int i=0; i < listaDeElementos.size(); i++) {
            PdfPCell celula;
            try {
                Method metodos[] = listaDeElementos.get(i).getClass().getMethods();
                while(j < colunas.keySet().size()) {
                    Method metodo = metodos[k];
                    if(metodo.isAnnotationPresent(Coluna.class)) {
                        if(metodo.getAnnotation(Coluna.class).posicao() == j) {
                            celula = new PdfPCell(new Phrase((String)metodo.invoke(listaDeElementos.get(i), null)));
                            tabela.addCell(celula);
                            j++;
                            k = 0;
                        } else {
                            k++;
                        }
                    } else {
                        k++;
                    }
                }
            } catch(IllegalAccessException e) {

            } catch(IllegalArgumentException e) {

            } catch(InvocationTargetException e) {

            }
            j = 0;
        }
                
                
                /*for(PropertyDescriptor propertyDescriptor : 
                    Introspector.getBeanInfo(listaDeElementos.get(i).getClass(), Object.class).getPropertyDescriptors()){

                    if(propertyDescriptor.getReadMethod().getReturnType().equals(String.class)) {

                        celula = new PdfPCell(new Phrase((String)propertyDescriptor.getReadMethod().invoke(listaDeElementos.get(i), null)));
                        tabela.addCell(celula);
                    }
                }*/
            //} catch(IntrospectionException e) {


    }
    
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
