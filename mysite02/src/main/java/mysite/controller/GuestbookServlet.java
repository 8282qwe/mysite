package mysite.controller;

import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.ActionServlet;
import mysite.controller.action.guestbook.DeleteAction;
import mysite.controller.action.guestbook.DeleteFormAction;
import mysite.controller.action.guestbook.InsertAction;
import mysite.controller.action.guestbook.ListAction;

import java.io.Serial;
import java.util.Map;

@WebServlet("/guestbook")
public class GuestbookServlet extends ActionServlet {

    @Serial
    private static final long serialVersionUID = -2518453613401269766L;

    private Map<String, Action> mapAction = Map.of("insert",new InsertAction(),"deleteform",new DeleteFormAction(),"delete",new DeleteAction());

    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new ListAction());
    }
}