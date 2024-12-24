package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class ListAction implements ActionServlet.Action {
    private final int PER_PAGE = 5;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = Optional.ofNullable(req.getParameter("kwd")).orElse("");
        int currentPage = Integer.parseInt(Optional.ofNullable(req.getParameter("page")).orElse("1"));
        int boardCount = new BoardDao().countBoard(keyword);
        int totalPage = (int) Math.ceil((double) boardCount / PER_PAGE);
        int prevPage = ((currentPage - 1) / 5) * 5;
        int endPage = Math.min((prevPage + 5), totalPage);

        Map<String, Integer> pagingData = Map.of("currentPage", currentPage, "totalPage", totalPage, "prevPage", prevPage, "endPage", endPage,"totalCount",boardCount,"perPage",PER_PAGE);
        req.setAttribute("pagingData", pagingData);
        req.setAttribute("boardList", new BoardDao().findAllBoard(currentPage, PER_PAGE ,keyword));
        req.setAttribute("kwd", keyword);
        req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
    }
}
