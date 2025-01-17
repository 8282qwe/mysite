package mysite.controller;

import jakarta.servlet.http.HttpServletResponse;
import mysite.security.Auth;
import mysite.security.AuthUser;
import mysite.service.BoardService;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping({"", "/"})
    public String board(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "kwd", defaultValue = "") String kwd) {

        Map<String, Object> data = boardService.getContentsList(page, kwd);
        model.addAttribute("pagingData", data.get("pagingData"));
        model.addAttribute("boardList", data.get("list"));

        return "board/list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam("id") Long id,
                       @RequestParam("currentPage") Integer currentPage,
                       @CookieValue(value = "viewPage", required = false, defaultValue = "") String viewPage,
                       HttpServletResponse response,
                       Model model) {

        response.addCookie(boardService.countView(id, viewPage));
        model.addAttribute("board", boardService.getContents(id));
        model.addAttribute("currentPage", currentPage);
        return "board/view";
    }

    @Auth
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String viewWrite(@RequestParam(value = "id", required = false) Long id, Model model) {

        if (id != null) {
            model.addAttribute("board", boardService.getContents(id));
        }
        return "board/write";
    }

    @Auth
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@AuthUser UserVo authUser, BoardVo vo) {

        vo.setUser_id(authUser.getId());

        boardService.addContents(vo);
        return "redirect:/board";
    }

    @Auth
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String viewModify(@AuthUser UserVo authUser, @RequestParam("id") Long id, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {
        BoardVo board = boardService.getContents(id);

        if (!authUser.getId().equals(board.getUser_id())) {
            return "redirect:/board?page=" + currentPage;
        }

        model.addAttribute("board", board);
        model.addAttribute("currentPage", currentPage);
        return "board/modify";
    }

    @Auth
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@AuthUser UserVo authUser, BoardVo vo, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {

        vo.setUser_id(authUser.getId());

        boardService.updateContents(vo);
        model.addAttribute("currentPage", currentPage);
        return "redirect:/board/view?id=" + vo.getId() + "&currentPage=" + currentPage;
    }

    @Auth
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@AuthUser UserVo authUser, @PathVariable("id") Long id, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {

        boardService.deleteContents(id, authUser.getId());
        model.addAttribute("currentPage", currentPage);
        return "redirect:/board?page=" + currentPage;
    }
}
