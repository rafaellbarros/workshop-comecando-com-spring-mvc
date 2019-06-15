$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);

    const codigoTitulo = button.data('codigo');
    const descricaoTitulo = button.data('descricao');

    const modal = $(this);

    const form = modal.find('form');

    let action = form.attr('action');

    if (!action.endsWith('/')) {
        action += '/';
    }

    form.attr('action', action + codigoTitulo);

    modal.find('.modal-body').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');

})