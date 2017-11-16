package com.cu.service.impl;

import com.cu.dao.ProblemDao;
import com.cu.model.Problem;
import com.cu.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 故障现象业务逻辑实现类
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemDao problemDao;

    @Override
    public List<Problem> queryAll() {
        return problemDao.queryAll();
    }

    @Override
    public int countAll() {
        return problemDao.countAll();
    }

    @Override
    public List<Problem> queryByIdList(int[] pids) {
        return problemDao.queryByIdList(pids);
    }

    @Override
    public Problem queryById(int problem_id) {
        return problemDao.queryById(problem_id);
    }

    @Override
    public List<Problem> queryLike(String problem) {
        return problemDao.queryLike(problem);
    }

    @Override
    public List<Problem> queryContentKey() {
        return problemDao.queryContentKey();
    }

    @Override
    public List<Problem> queryByIdAndProblem(int problem_id, String problem) {
        return problemDao.queryByIdAndProblem(problem_id,problem);
    }

    @Override
    public List<Problem> queryLikeProblemAndContentKey(String problem, String content_key) {
        return problemDao.queryLikeProblemAndContentKey(problem,content_key);
    }

    @Override
    public List<Problem> queryLikeContentKey(String content_key) {
        return problemDao.queryLikeContentKey(content_key);
    }

    @Override
    public List<Problem> queryLikeProblem(String problem) {
        return problemDao.queryLikeProblem(problem);
    }

    @Override
    public Problem queryByProblem(String problem) {
        return problemDao.queryByProblem(problem);
    }

    @Override
    public void insertProblem(String problem) {
        problemDao.insertProblem(problem);
    }
}
