package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.dto.Result;
import com.example.english_test.entity.Word;
import com.example.english_test.entity.WordsSelect;
import com.example.english_test.mapper.WordMapper;
import com.example.english_test.service.IWordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements IWordService {


    @Override
    public Result get_Words_List() {
        /*生成去重随即列表*/
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        int i=0;
        while(i<20)
        {
            int id = random.nextInt(1000)+1;
            if (!list.contains(id))
            {
                list.add(id);
                i++;
            }
        }
        /*根据生成的id列表抽取单词
        * */
        List<Word> words = listByIds(list);

        /*处理抽取的单词,包装成包含一个正确选项和三个错误选项的单词
        *
        * */
        ArrayList<WordsSelect> wordsSelects = new ArrayList<>();
        for (int i1 = 0; i1 < words.size(); i1++) {
            WordsSelect wordsSelect = new WordsSelect();
            wordsSelect.setWordId(words.get(i1).getWordId());
            wordsSelect.setWordSpell(words.get(i1).getWordSpell());
            wordsSelect.setWordMeaning(words.get(i1).getWordMeaning());
            HashSet<String> selects1 = new HashSet<>();
            selects1.add(words.get(i1).getWordMeaning());
            int j=0;
           while(j<3)
            {
                if (selects1.add(words.get(random.nextInt(words.size())).getWordMeaning()))
                {
                    j++;
                }
            }

            ArrayList<String> select = new ArrayList<>(selects1);
            Collections.shuffle(select);
            wordsSelect.setErrorMeaning(select);
            wordsSelects.add(wordsSelect);
        }
        return Result.ok(wordsSelects);
    }
}
