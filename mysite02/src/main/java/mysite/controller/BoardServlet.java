package mysite.controller;

import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.ActionServlet;
import mysite.controller.action.board.*;

import java.io.Serial;
import java.util.Map;

@WebServlet("/board")
public class BoardServlet extends ActionServlet {
    @Serial
    private static final long serialVersionUID = -2518453613401269766L;

    private Map<String, Action> mapAction = Map.of("writeform",new WriteFormAction(),
            "write",new WriteAction(),
            "view",new ViewFormAction(),
            "delete",new DeleteAction(),
            "modifyform", new ModifyFormAction(),
            "modify",new ModifyAction());

    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new ListAction());
    }
}
