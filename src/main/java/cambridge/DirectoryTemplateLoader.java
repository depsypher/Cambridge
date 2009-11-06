package cambridge;

import java.io.File;

/**
 * User: erdinc
 * Date: Nov 3, 2009
 * Time: 6:15:47 PM
 */
public class DirectoryTemplateLoader extends FileTemplateLoader {
   File templateDirectory;
   String encoding;

   static String fileSeperator = System.getProperty("file.separator");

   public DirectoryTemplateLoader(File templateDirectory) {
      this.templateDirectory = templateDirectory;
      if (!templateDirectory.isDirectory()) {
         throw new RuntimeException(templateDirectory + " is not a directory");
      }

      encoding = FileTemplateLoader.DefaultEncoding;
   }

   public DirectoryTemplateLoader(File templateDirectory, String defaultEncoding) {
      this.templateDirectory = templateDirectory;
      if (!templateDirectory.isDirectory()) {
         throw new RuntimeException(templateDirectory + " is not a directory");
      }

      encoding = defaultEncoding;
   }

   public TemplateFactory newTemplateFactory(String template) throws TemplateLoadingException {
      return newTemplateFactory(template, encoding);
   }

   public TemplateFactory newTemplateFactory(String template, String encoding) throws TemplateLoadingException {
      File templateFile = new File(templateDirectory.getAbsolutePath() + fileSeperator + template);
      return FileTemplateLoader.newTemplateFactory(templateFile, encoding);
   }

   public TemplateFactory newTemplateFactory(String template, TemplateModifier modifier) throws TemplateLoadingException {
      return newTemplateFactory(template, encoding, modifier);
   }

   public TemplateFactory newTemplateFactory(String template, String encoding, TemplateModifier modifier) throws TemplateLoadingException {
      File templateFile = new File(templateDirectory.getAbsolutePath() + fileSeperator + template);
      return FileTemplateLoader.newTemplateFactory(templateFile, encoding, modifier);
   }
}