package com.cg.controller;

import com.cg.model.dto.ITransferDTO;
import com.cg.model.dto.SumFreeAmountDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class TransferController {

    @Autowired
    private ITransferService transferService;

    @GetMapping("transferList")
    public ModelAndView listTransfer(){
        ModelAndView modelAndView = new ModelAndView("transfer/list");

        Iterable<ITransferDTO> iTransferDTOS = transferService.findAllByITransferDTO();

        Optional<SumFreeAmountDTO> sumFreeAmountDTO = transferService.sumFeesAmount();

       modelAndView.addObject("iTransferDTO", iTransferDTOS);
       modelAndView.addObject("sumAmount", sumFreeAmountDTO.get());
        return modelAndView;
    }
}
