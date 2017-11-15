//package com.examples.weibao.htmls;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.PicturesManager;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import org.apache.poi.hwpf.usermodel.PictureType;
//
//
//import org.apache.poi.xwpf.converter.core.BasicURIResolver;
//import org.apache.poi.xwpf.converter.core.FileImageExtractor;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.w3c.dom.Document;
//
///**
// *
// * @author ruand
// * http://www.lai18.com/content/5505682.html
// */
//public class POIWordToHtml {
//	private static final String ENCODING = "GB2312";// UTF-8
//
//	public static String wordToHtml(String sourcePath, final String picturesPath, String targetPath){
//		String ext = FileUtils.GetFileExt(sourcePath);
//		File picturesDir = new File(picturesPath);
//		if (!picturesDir.isDirectory()) {
//			picturesDir.mkdirs();
//		}
//		String content = null;
//		try {
//			if (ext.equals("doc")) {
//
//				HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourcePath));
//				WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//						DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
//				wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//					@Override
//					public String savePicture(byte[] content, PictureType pictureType, String suggestedName,
//							float widthInches, float heightInches) {
//						File file = new File(picturesPath + "\\" + suggestedName);
//						FileOutputStream fos = null;
//						try {
//							fos = new FileOutputStream(file);
//							fos.write(content);
//							fos.close();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						return picturesPath + "/" + suggestedName;
//					}
//				});
//				wordToHtmlConverter.processDocument(wordDocument);
//				Document htmlDocument = wordToHtmlConverter.getDocument();
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				DOMSource domSource = new DOMSource(htmlDocument);
//				StreamResult streamResult = new StreamResult(out);
//
//				TransformerFactory tf = TransformerFactory.newInstance();
//				Transformer serializer = tf.newTransformer();
//				serializer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
//				serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//				serializer.setOutputProperty(OutputKeys.METHOD, "html");
//				serializer.transform(domSource, streamResult);
//				out.close();
//				FileUtils.writeFile(new String(out.toByteArray()), targetPath+"dddk.html");
//				content = out.toString();
//				System.out.println("ggggggg");
//			} else if (ext.equals("docx")) {
//				// 1) ����word�ĵ����� XWPFDocument����
//				InputStream in = new FileInputStream(new File(sourcePath));
//				XWPFDocument document = new XWPFDocument(in);
//				// 2) ���� XHTML���� (��������IURIResolver������ͼƬ��ŵ�Ŀ¼)
//				XHTMLOptions options = XHTMLOptions.create();
//				options.setExtractor(new FileImageExtractor(picturesDir));
//				options.URIResolver(new BasicURIResolver(picturesPath));
//				// 3) �� XWPFDocumentת����XHTML
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				XHTMLConverter.getInstance().convert(document, baos, options);
//				baos.close();
//				content = baos.toString();
//				FileUtils.writeFile(content, targetPath);
//				System.out.println("*****docxתhtml dsfdsfsdfsd...*****");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return content;
//	}
//
//
//}
