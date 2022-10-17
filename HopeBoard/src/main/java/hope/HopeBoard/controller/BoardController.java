package hope.HopeBoard.controller;

import hope.HopeBoard.entity.Board;
import hope.HopeBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("")
    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board , Model model , MultipartFile file) throws Exception {

        boardService.write(board , file);

        model.addAttribute("message" , "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl" , "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model ,
                            @PageableDefault(page = 0 , size = 10  , direction = Sort.Direction.DESC) Pageable pageable ,
                            String searchKeyword) {

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword , pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(startPage + 5 , list.getTotalPages());

        model.addAttribute("list" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model , Integer b_id) {
        model.addAttribute("board" , boardService.boardView(b_id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer b_id) {

        boardService.boardDelete(b_id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{b_id}")
    public String boardModify(@PathVariable("b_id") Integer b_id , Model model){

        model.addAttribute("board" , boardService.boardView(b_id));

        return "boardmodify";
    }

    @PostMapping("/board/update/{b_id}")
    public String boardUpdate(@PathVariable("b_id") Integer b_id, Board board, MultipartFile file) throws Exception {

        Board boardTemp = boardService.boardView(b_id);
        boardTemp.setB_title(board.getB_title());
        boardTemp.setB_content(board.getB_content());

        boardService.write(boardTemp ,file);

        return "redirect:/board/list";
    }
}
