package pl.dsw.dwolkowski.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dsw.dwolkowski.api.metadata.exception.EntityNotFoundException;
import pl.dsw.dwolkowski.api.metadata.model.Reader;
import pl.dsw.dwolkowski.api.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders(){ return readerRepository.findAll(); }

    public Reader getReader(long reader_id){ return readerRepository.findById(reader_id).orElseThrow(EntityNotFoundException::new); }

    public Reader createReader(Reader newReader){ return readerRepository.save(newReader); }

    public void deleteReader(long reader_id){
        readerRepository.findById(reader_id).orElseThrow(EntityNotFoundException::new);
        readerRepository.deleteById(reader_id);
    }
}
