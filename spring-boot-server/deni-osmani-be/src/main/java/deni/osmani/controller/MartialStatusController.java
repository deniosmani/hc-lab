package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dto.MartialStatusDto;
import deni.osmani.service.MartialStatusService;

@RestController
@RequestMapping("martialstatuses")
@CrossOrigin(origins = "*")
public class MartialStatusController {

	@Autowired
	private MartialStatusService martialStatusService;
	@GetMapping
	public List<MartialStatusDto> findAll() {
		return martialStatusService.findAll();
	}
}
