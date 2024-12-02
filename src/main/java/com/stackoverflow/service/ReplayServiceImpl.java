package com.stackoverflow.service;

import com.stackoverflow.model.Replay;
import com.stackoverflow.repository.ReplayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplayServiceImpl implements ReplayService{
    ReplayRepository replayRepository;

    ReplayServiceImpl(ReplayRepository replayRepository){
        this.replayRepository=replayRepository;
    }

    @Override
    public Replay saveReplay(Replay replay) {
        return replayRepository.save(replay);
    }

    @Override
    public Replay getReplayById(Long id) {
        return replayRepository.findById(id).get();
    }

    @Override
    public List<Replay> getAllReplay() {
        return replayRepository.findAll();
    }
}
