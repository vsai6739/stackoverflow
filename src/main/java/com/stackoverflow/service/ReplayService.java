package com.stackoverflow.service;

import com.stackoverflow.model.Discussion;
import com.stackoverflow.model.Replay;

import java.util.List;

public interface ReplayService {
    Replay saveReplay(Replay replay);
    Replay getReplayById(Long id);
    List<Replay> getAllReplay();
}
