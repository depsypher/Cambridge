package cambridge.behaviors;

import cambridge.StaticBehavior;
import cambridge.model.SimpleAttribute;
import cambridge.model.Tag;
import cambridge.model.TagNode;
import cambridge.model.TemplateDocument;

import java.util.ArrayList;

/**
 * User: erdinc
 * Date: Nov 2, 2009
 * Time: 3:59:27 PM
 */
public class AltAdderStaticBehavior implements StaticBehavior {
   public void modify(TemplateDocument doc, String value, TagNode t) {
      ArrayList<Tag> tags = t.getElementsByTagName("img");
      for (Tag tag : tags) {
         if (!tag.hasAttribute("alt")) {
            tag.addAttribute(new SimpleAttribute("alt", ""));
         }
      }
      if (t.getTagName().equalsIgnoreCase("img")) {
         if (!t.hasAttribute("alt")) {
            t.addAttribute(new SimpleAttribute("alt", ""));
         }
      }
   }

   public boolean shouldRemove() {
      return false;
   }
}
