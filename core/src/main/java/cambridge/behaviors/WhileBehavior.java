package cambridge.behaviors;

import cambridge.*;
import cambridge.runtime.TemplateProperties;
import cambridge.runtime.Iter;
import cambridge.model.Attribute;
import cambridge.model.DynamicAttribute;
import cambridge.model.TagNode;
import cambridge.parser.expressions.Expression;

import java.io.IOException;
import java.util.Map;


/**
 * User: erdinc
 * Date: Nov 1, 2009
 * Time: 2:36:22 PM
 */
public class WhileBehavior extends ExecutingTagBehavior {
   private final Expression expression;

   public WhileBehavior(Expression expression) {
      this.expression = expression;
   }

   @Override
   public void doExecute(TemplateProperties properties, TagNode tag, Appendable out) throws TemplateEvaluationException, IOException {
      try {
         Iter iter = new Iter();
         while (expression.asBoolean(properties)) {
            properties.put("#iter", iter);
            tag.execute(properties, out);
            iter.next();
         }
      } catch (ExpressionEvaluationException e) {
         throw new TemplateEvaluationException("Could not execute the expression: " + e.getMessage(), tag.getBeginLine(), tag.getBeginColumn(), tag.getTagName());
      }
   }

   public static BehaviorProvider<WhileBehavior> getProvider() {
      return new BehaviorProvider<WhileBehavior>() {
         public WhileBehavior get(DynamicAttribute keyAttribute, Map<AttributeKey, Attribute> attributes) throws ExpressionParsingException, BehaviorInstantiationException {
            return new WhileBehavior(keyAttribute.getExpression());
         }
      };
   }
}