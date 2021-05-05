package com.company;

import java.io.*;

public class Pergunta implements Register {

    private int idPergunta;
    private int idUsuario;
    private long criacao;
    private short nota;
    private String pergunta;
    private boolean ativa;

    public Pergunta() {
        this.idPergunta = -1;
        this.idUsuario = -1;
        this.criacao = -1;
        this.nota = -1;
        this.pergunta = "";
        this.ativa = false;
    }

    public Pergunta(int idUsuario, String pergunta) {
        this.idPergunta = -1;
        this.idUsuario = idUsuario;
        this.criacao = criacao;
        this.nota = nota;
        this.pergunta = pergunta;
        this.ativa = true;
    }

    @Override
    public int getID() {
        return idPergunta;
    }

    @Override
    public void setID(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getCriacao() {
        return criacao;
    }

    public void setCriacao(long criacao) {
        this.criacao = criacao;
    }

    public short getNota() {
        return nota;
    }

    public void setNota(short nota) {
        this.nota = nota;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(idPergunta);
        dos.writeInt(idUsuario);
        dos.writeLong(criacao);
        dos.writeShort(nota);
        dos.writeUTF(pergunta);
        dos.writeBoolean(ativa);
        dos.close();
        baos.close();

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        idPergunta = dis.readInt();
        idUsuario = dis.readInt();
        criacao = dis.readLong();
        nota = dis.readShort();
        pergunta = dis.readUTF();
        ativa = dis.readBoolean();
    }
}
