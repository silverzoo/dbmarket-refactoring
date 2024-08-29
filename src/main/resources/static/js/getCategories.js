document.addEventListener('DOMContentLoaded', () => {
    const categorySelect = document.getElementById('category');

    fetch('/api/categories')
        .then(response => response.json())
        .then(categories => {
            // Clear existing options
            console.log(categories)
            categorySelect.innerHTML = '<option value="" selected disabled hidden>카테고리를 선택해주세요</option>';

            // Add new options
            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.categoryId;
                option.textContent = category.name;
                categorySelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching categories:', error));
});